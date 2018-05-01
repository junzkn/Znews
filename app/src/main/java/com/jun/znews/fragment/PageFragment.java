package com.jun.znews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.znews.R;
import com.jun.znews.fragment.base.BaseFragment;

public class PageFragment extends BaseFragment {
    private View mView;
    private static final String KEY = "EXTRA" ;
    private String message;

    public static PageFragment newInstance(String extra) {
        Bundle bundle = new Bundle() ;
        bundle.putString(KEY,extra);
        PageFragment fragment = new PageFragment() ;
        fragment.setArguments(bundle);
        return fragment ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments() ;
        if(bundle!=null){
            message = bundle.getString(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView = inflater.inflate(R.layout.page_fragment_layout,container,false);
        }
        return mView ;
    }
}
