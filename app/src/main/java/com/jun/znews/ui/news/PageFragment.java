package com.jun.znews.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jun.znews.R;
import com.jun.znews.entity.NewsDetail;
import com.jun.znews.ui.adapter.PageRecycleAdapter;
import com.jun.znews.ui.base.BaseFragment;

import java.util.List;

public class PageFragment extends BaseFragment<IPresenter> implements IView {

    private static final String KEY = "EXTRA";
    private String message;
    private RecyclerView page_listview;
    private SwipeRefreshLayout srl;
    private LinearLayoutManager linearLayoutManager;
    private PageRecycleAdapter quickAdapter;

    public static PageFragment newInstance(String extra) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY, extra);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            message = bundle.getString(KEY);
        }
        basepresenter.loadData();
    }

    @Override
    public NewsPresenter initPresent() {
        return new NewsPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.page_fragment_layout;
    }

    @Override
    public void initView() {
        page_listview = mView.findViewById(R.id.page_listView);

        srl = mView.findViewById(R.id.srl);
        linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
    }

    @Override
    public void onPrepare() {
        quickAdapter = new PageRecycleAdapter(getActivity());
        page_listview.setHasFixedSize(true);
        page_listview.setLayoutManager(linearLayoutManager);
        srl.setProgressBackgroundColorSchemeResource(R.color.colorPrimaryDark);
        srl.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.darker_gray);
        srl.setProgressViewOffset(false, 0, 50);

    }

    @Override
    public void setAdapterData(List<NewsDetail.ItemBean> newsDetails) {
        quickAdapter.setItemBeans(newsDetails);
        page_listview.setAdapter(quickAdapter);
        int y = newsDetails.size();
        for (int i = 0; i <y ; i++) {
            Log.e("typr",newsDetails.get(i).getType()) ;
        }

    }

    @Override
    public void setMoreAdapterData(List<NewsDetail> newsDetails) {

    }
}
