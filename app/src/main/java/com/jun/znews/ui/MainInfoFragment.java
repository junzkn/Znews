package com.jun.znews.ui;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.znews.R;
import com.jun.znews.ui.adapter.FixedPagerAdapter;
import com.jun.znews.ui.base.BaseFragment;
import com.jun.znews.ui.news.NewsFragment;
import com.jun.znews.utils.ChannelUtils;

import java.util.ArrayList;
import java.util.List;

public class MainInfoFragment extends Fragment {
    private View mView ;
    private TabLayout tab_layout ;
    private ViewPager info_viewpager ;
    private FixedPagerAdapter fixedPagerAdapter;
    private String[] titles ;
    private List<BaseFragment> fragmentList ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView = inflater.inflate(R.layout.main_info_fragment_layout,container,false) ;
            initView() ;
            initValData() ;
            initListener() ;
        }
        return mView ;
    }


    public void initView() {
        titles = ChannelUtils.getALLChannelName(getContext());
        tab_layout = mView.findViewById(R.id.tab_layout) ;
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        info_viewpager = mView.findViewById(R.id.info_viewpager) ;
    }

    public void initValData() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setTitles(titles);
        fragmentList = new ArrayList<>();
        for(int i=0 ; i<titles.length ; i++){
            fragmentList.add(NewsFragment.newInstance(i));
        }
        fixedPagerAdapter.setFragments(fragmentList);
        info_viewpager.setAdapter(fixedPagerAdapter);
        tab_layout.setupWithViewPager(info_viewpager);
    }

    public void initListener() {
        info_viewpager.setOnPageChangeListener(new mPagerChangeListener());
    }


    private class mPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            MainActivity activity = (MainActivity)getActivity() ;
            activity.getDrag_layout().setDrag(position==0?true:false);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }





}























