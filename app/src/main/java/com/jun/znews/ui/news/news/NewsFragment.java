package com.jun.znews.ui.news.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.jun.znews.R;
import com.jun.znews.entity.NewsDetail;
import com.jun.znews.ui.adapter.NewsDetailAdapter;
import com.jun.znews.ui.base.BaseFragment;
import com.jun.znews.ui.news.content.AdvertReadActivity;
import com.jun.znews.ui.news.content.ArticleReadActivity;
import com.jun.znews.ui.news.content.ImageBrowseActivity;
import com.jun.znews.utils.ChannelUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsView {

    private static final String KEY = "EXTRA";
    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";
    int defaultNum = 0 ;
    int upNum = 0;
    int downNum = 0;
    private int index;
    private String channelID;
    private RecyclerView page_listview;
    private SwipeRefreshLayout srl;
    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout top_toast ;
    private TextView top_toast_text ;
    protected Button loadFail ;
    protected TextView  loadingData ;
    NewsDetailAdapter detailAdapter;
    List<NewsDetail.ItemBean> beanList;

    public static NewsFragment newInstance(int extra) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, extra);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(KEY);
        }
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
    public void init() {
        channelID = ChannelUtils.getALLChannelID(getContext())[index];
        beanList = new ArrayList<>();
        loadingData = mView.findViewById(R.id.img_loadingData) ;
        loadFail = mView.findViewById(R.id.img_loadFail) ;
        top_toast = mView.findViewById(R.id.top_toast);
        top_toast_text = mView.findViewById(R.id.top_toast_text);
        page_listview = mView.findViewById(R.id.page_listView);
        srl = mView.findViewById(R.id.srl);
        linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        detailAdapter = new NewsDetailAdapter(beanList, getActivity());
    }

    @Override
    public void prepare() {
        loading();
        loadFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                basePresenter.loadData(channelID, ACTION_DEFAULT, defaultNum);
            }
        });

        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        detailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                basePresenter.loadUpData(channelID, ACTION_UP, upNum);
            }
        }, page_listview);

        page_listview.setAdapter(detailAdapter);
        page_listview.setHasFixedSize(true);
        page_listview.setLayoutManager(linearLayoutManager);

        detailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetail.ItemBean itemBean =  detailAdapter.getItem(position);
                Log.e("newsBean:",itemBean.toString());
                toRead(itemBean);
            }
        });
        detailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetail.ItemBean itemBean =  detailAdapter.getItem(position);
                switch (view.getId()) {
                    case R.id.iv_close :
                        if (itemBean.getStyle() == null) return;
                        //TODO
                        break ;
                }
            }
        });



        srl.setProgressViewOffset(false, 0, 100);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                basePresenter.loadData(channelID, ACTION_DOWN, downNum);
            }
        });


        basePresenter.loadData(channelID, ACTION_DEFAULT, defaultNum);
    }


    public void loading(){
        loadingData.setVisibility(View.VISIBLE);
        loadFail.setVisibility(View.GONE);
    }
    public void loadSucceed(){
        loadingData.setVisibility(View.GONE);
        loadFail.setVisibility(View.GONE);
    }
    public void loadFail(){
        loadFail.setVisibility(View.VISIBLE);
        loadingData.setVisibility(View.GONE);
    }




    @Override
    public void setAdapterData(List<NewsDetail.ItemBean> newsDetails) {
        if (newsDetails == null || newsDetails.size() == 0) {
            if (srl.isRefreshing()) {
                srl.setRefreshing(false);
                showNumber(-1);
            }
            else {
                loadFail();
            }
        }else {
            if (srl.isRefreshing()) {
                downNum++;
                srl.setRefreshing(false);
            }
            loadSucceed();
            detailAdapter.setNewData(newsDetails);
        }
    }

    @Override
    public void setMoreAdapterData(List<NewsDetail.ItemBean> newsDetails) {
        if (detailAdapter.isLoading()) {
            detailAdapter.loadMoreComplete();
        }
        if (newsDetails == null || newsDetails.size() == 0) {
            detailAdapter.loadMoreFail();
        } else {
            upNum++;
            detailAdapter.addData(newsDetails);
        }
    }

    @Override
    public void showNumber(int number) {
        if(number==-1){
            top_toast_text.setText(R.string.news_toast_noData);
        } else {
            top_toast_text.setText(String.format(getResources().getString(R.string.news_toast), number + ""));
        }
        top_toast.setVisibility(View.VISIBLE);
        ViewAnimator.animate(top_toast)
                .newsPaper()
                .duration(1000)
                .start()
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        ViewAnimator.animate(top_toast)
                                .bounceOut()
                                .duration(1000)
                                .start();
                    }
                });

    }

    @Override
    public  LifecycleTransformer bindToLife() {
        return this.bindToLifecycle();
    }



    private void toRead(NewsDetail.ItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getItemType()) {
            case NewsDetail.ItemBean.TYPE_DOC_TITLEIMG:
            case NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG:
                Intent intent = new Intent(getActivity(), ArticleReadActivity.class);
                intent.putExtra("aid", itemBean.getDocumentId());
                startActivity(intent);
                break;
            case NewsDetail.ItemBean.TYPE_SLIDE:
                ImageBrowseActivity.launch(getActivity(), itemBean);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG:
            case NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG:
            case NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG:
                AdvertReadActivity.launch(getActivity(), itemBean.getLink().getWeburl());
                break;
            case NewsDetail.ItemBean.TYPE_PHVIDEO:
                Toast.makeText(getContext(),"TYPE_PHVIDEO",Toast.LENGTH_SHORT);
                break;
        }
    }



}
