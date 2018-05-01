package com.jun.znews.fragment;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.znews.R;
import com.jun.znews.adapter.FixedPagerAdapter;
import com.jun.znews.common.DefineView;
import com.jun.znews.fragment.base.BaseFragment;
import com.jun.znews.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainInfoFragment extends BaseFragment implements DefineView{
    private View mView ;
    private TabLayout tab_layout ;
    private ViewPager info_viewpager ;
    private FixedPagerAdapter fixedPagerAdapter;
    private String[] titles = new String[] {"新鲜事","无聊图","妹子图","段子"};
    private List<BaseFragment> fragmentList ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView = inflater.inflate(R.layout.main_info_fragment_layout,container,false) ;
            initView() ;
            initValData() ;
            initListener() ;
            bindData() ;
        }
        return mView ;
    }


    @Override
    public void initView() {
        tab_layout = mView.findViewById(R.id.tab_layout) ;
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        info_viewpager = mView.findViewById(R.id.info_viewpager) ;
    }

    @Override
    public void initValData() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setTitles(titles);
        fragmentList = new ArrayList<>();
        for(int i=0 ; i<titles.length ; i++){
            fragmentList.add(PageFragment.newInstance(titles[i]));
        }
        fixedPagerAdapter.setFragments(fragmentList);
        info_viewpager.setAdapter(fixedPagerAdapter);
        tab_layout.setupWithViewPager(info_viewpager);
    }

    @Override
    public void initListener() {
        info_viewpager.setOnPageChangeListener(new mPagerChangeListener());
    }

    @Override
    public void bindData() {

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























