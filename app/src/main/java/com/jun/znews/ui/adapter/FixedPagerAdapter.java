package com.jun.znews.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import com.jun.znews.ui.base.BaseFragment;

import java.util.List;

public class FixedPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles ;
    private List<BaseFragment> fragmentList ;


    public void setTitles(String[] titles){
        this.titles = titles ;
    }
    public void setFragments(List<BaseFragment> fragmentsList){
        this.fragmentList = fragmentsList ;
    }
    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
