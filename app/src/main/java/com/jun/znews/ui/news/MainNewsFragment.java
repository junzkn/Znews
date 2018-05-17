package com.jun.znews.ui.news;



import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jun.znews.R;
import com.jun.znews.bean.Channel;
import com.jun.znews.db.ChannelDao;
import com.jun.znews.event.NewChannelEvent;
import com.jun.znews.event.SelectChannelEvent;
import com.jun.znews.ui.main.MainActivity;
import com.jun.znews.ui.adapter.MainNewsAdapter;
import com.jun.znews.ui.base.BaseFragment;
import com.jun.znews.ui.news.Contract.IMainNewsContarct;
import com.jun.znews.ui.news.Presenter.MainNewsPresenter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MainNewsFragment extends Fragment implements IMainNewsContarct.IMainNewsView {
    private View mView ;
    private TabLayout tab_layout ;
    private ViewPager info_viewpager ;
    private MainNewsAdapter mainNewsAdapter;
    private List<BaseFragment> fragmentList ;
    private Button add_more;
    private List<Channel> mSelectedDatas;
    private List<Channel> mUnSelectedDatas;
    private IMainNewsContarct.IMainNewsPresenter mPresenter ;

    private int selectedIndex = 0;
    private String selectedChannel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView = inflater.inflate(R.layout.main_info_fragment_layout,container,false) ;
            init() ;
            prepare() ;
        }
        return mView ;
    }


    public void init() {
        mPresenter = new MainNewsPresenter(this);
        tab_layout = mView.findViewById(R.id.tab_layout) ;
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        info_viewpager = mView.findViewById(R.id.info_viewpager) ;
        add_more = mView.findViewById(R.id.add_more);
        mUnSelectedDatas = new ArrayList<>();
        mSelectedDatas = new ArrayList<>();
        mainNewsAdapter = new MainNewsAdapter(getChildFragmentManager());
        fragmentList = new ArrayList<>();

    }


    public void prepare() {
        tab_layout.setupWithViewPager(info_viewpager);
        info_viewpager.addOnPageChangeListener(new mPagerChangeListener());
        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
            }
        });

        mPresenter.getChannel();

    }


    @Subscriber
    private void updateChannel(NewChannelEvent event) {
        if (event == null) return;
        if (event.selectedDatas != null && event.unSelectedDatas != null) {
            mSelectedDatas = event.selectedDatas;
            mUnSelectedDatas = event.unSelectedDatas;
            ChannelDao.saveChannels(event.allChannels);
            ArrayList<String> titleList = new ArrayList<>();
            for(Channel channel : mSelectedDatas){
                titleList.add(channel.getChannelName()) ;
            }
            String[] titles = new String[titleList.size()] ;
            titleList.toArray(titles);
            fragmentList.clear();
            mainNewsAdapter.setTitles(titles);
            for(int i=0 ; i<titles.length ; i++){
                fragmentList.add(NewsFragment.newInstance(mSelectedDatas.get(i).getChannelId()));
            }
            mainNewsAdapter.setFragments(fragmentList);
            mainNewsAdapter.notifyDataSetChanged();


            if (TextUtils.isEmpty(event.firstChannelName)) {
                if (!titleList.contains(selectedChannel)) {
                    selectedIndex=selectedIndex<titleList.size()?selectedIndex:0 ;
                    selectedChannel = mSelectedDatas.get(selectedIndex).getChannelName();
                    info_viewpager.setCurrentItem(selectedIndex, false);
                } else {
                    setViewpagerPosition(titleList, selectedChannel);
                }
            } else {
                setViewpagerPosition(titleList, event.firstChannelName);
            }
        }
    }

    @Subscriber
    private void selectChannelEvent(SelectChannelEvent selectChannelEvent) {
        if (selectChannelEvent == null) return;
        List<String> titleList = new ArrayList<>();
        for (Channel channel : mSelectedDatas) {
            titleList.add(channel.getChannelName());
        }
        setViewpagerPosition(titleList, selectChannelEvent.channelName);
    }


    private void setViewpagerPosition(List<String> titleList, String channelName) {
        if (TextUtils.isEmpty(channelName) || titleList == null) return;
        for (int j = 0; j < titleList.size(); j++) {
            if (titleList.get(j).equals(channelName)) {
                selectedChannel = titleList.get(j);
                selectedIndex = j;
                break;
            }
        }
        info_viewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                info_viewpager.setCurrentItem(selectedIndex, false);
            }
        }, 100);
    }

    @Override
    public void setData(List<Channel> channels, List<Channel> unSelectedDatas) {
        if (channels != null) {
            mSelectedDatas.clear();
            mSelectedDatas.addAll(channels);
            mUnSelectedDatas.clear();
            mUnSelectedDatas.addAll(unSelectedDatas);
            ArrayList<String> titleList = new ArrayList<>();
            for(int i=0 ; i<mSelectedDatas.size() ; i++){
                titleList.add(mSelectedDatas.get(i).getChannelName()) ;
            }
            String[] titles = new String[titleList.size()] ;
            titleList.toArray(titles);
            mainNewsAdapter.setTitles(titles);

            fragmentList.clear();
            for(int i=0 ; i<titles.length ; i++){
                fragmentList.add(NewsFragment.newInstance(mSelectedDatas.get(i).getChannelId()));
            }
            mainNewsAdapter.setFragments(fragmentList);
            info_viewpager.setAdapter(mainNewsAdapter);
        } else {
            //TODO
        }
    }


    private class mPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            selectedIndex = position;
            selectedChannel = mSelectedDatas.get(position).getChannelName();
            MainActivity activity = (MainActivity) getActivity();
            activity.getDrag_layout().setDrag(position==0?true:false);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }

    }


    @Override
    public void onDestroy() {
        // 注销
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }



}























