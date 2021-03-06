package com.jun.znews.ui.news;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.jun.znews.R;
import com.jun.znews.bean.NewsDetail;
import com.jun.znews.ui.adapter.DeleteGridViewAdapter;
import com.jun.znews.ui.adapter.NewsAdapter;
import com.jun.znews.ui.base.BaseFragment;
import com.jun.znews.ui.main.MainActivity;
import com.jun.znews.ui.news.Contract.INewsContract;
import com.jun.znews.ui.news.Presenter.NewsPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsContract.INewsView {

    private static final String KEY = "EXTRA";
    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";
    int defaultNum = 0 ;
    int upNum = 0;
    int downNum = 0;
    private String index;
    private String channelID;
    private RecyclerView page_listview;
    private SwipeRefreshLayout srl;
    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout top_toast ;
    private TextView top_toast_text ;
    protected View loadFail ;
    protected View  loadingData ;
    Dialog delDialog;
    NewsAdapter detailAdapter;
    List<NewsDetail.ItemBean> beanList;

    public static NewsFragment newInstance(String extra) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY, extra);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getString(KEY);
        }
        channelID = index;
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

        beanList = new ArrayList<>();
        loadingData = mView.findViewById(R.id.img_loadingData) ;
        loadFail = mView.findViewById(R.id.img_loadFail) ;
        top_toast = mView.findViewById(R.id.top_toast);
        top_toast_text = mView.findViewById(R.id.top_toast_text);
        page_listview = mView.findViewById(R.id.page_listView);
        srl = mView.findViewById(R.id.srl);
        linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        detailAdapter = new NewsAdapter(beanList, getActivity());
    }

    @SuppressLint("ResourceAsColor")
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
                Log.d("newsBean:",itemBean.toString());
                toRead(itemBean);
            }
        });
        detailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetail.ItemBean itemBean =  detailAdapter.getItem(position);
                switch (view.getId()) {
                    case R.id.iv_close :
                        if (itemBean.getStyle() == null) break ;
                        delDialog = showDel(itemBean.getStyle().getBackreason(),position);
                        delDialog.show();
                        break ;
                }
            }
        });


        page_listview.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                MainActivity activity = (MainActivity) getActivity();
                if(velocityY>50){
                    activity.getTopbar().setVisibility(View.GONE);
                }else if(velocityY<-80){
                    activity.getTopbar().setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        srl.setColorSchemeResources(R.color.themeColor);
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
        if(number==-2){
            top_toast_text.setText(R.string.news_toast_delete);
        }
        else if(number==-1){
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
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("aid", itemBean.getDocumentId());
                startActivity(intent);
                break;
            case NewsDetail.ItemBean.TYPE_SLIDE:
                ImageBrowseActivity.launch(getActivity(), itemBean);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG:
            case NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG:
            case NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG:
                AdvertActivity.launch(getActivity(), itemBean.getLink().getWeburl());
                break;
            case NewsDetail.ItemBean.TYPE_PHVIDEO:
                //TODO
                Intent intent2 = new Intent(getActivity(), VideoActivity.class);
                intent2.putExtra("guid", itemBean.getId());
                startActivity(intent2);
                break;
        }
    }


    private Dialog showDel(List<String> backreason, final int position){
        final List<Boolean>select = new ArrayList<>() ;
        for(int i=0 ; i<backreason.size() ; i++){
            select.add(false) ;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = View.inflate(getContext(),R.layout.delpop, null);
        GridView gl = view.findViewById(R.id.gl);
        TextView btn_commit = view.findViewById(R.id.btn_commit);
        DeleteGridViewAdapter adapter = new DeleteGridViewAdapter(backreason,getContext()) ;
        gl.setAdapter(adapter);
        gl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                TextView delReason = view.findViewById(R.id.delReason);
                if(p!=0){
                    if(select.get(p)==false){
                        delReason.setTextColor(getResources().getColor(R.color.colorRed));
                        delReason.setBackground(getContext().getResources().getDrawable(R.drawable.delpop_tv_selected_bg));
                        select.set(p,true) ;
                    }else {
                        delReason.setTextColor(getResources().getColor(R.color.colorBlack));
                        delReason.setBackground(getContext().getResources().getDrawable(R.drawable.delpop_tv_bg));
                        select.set(p,false) ;
                    }
                }

            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delDialog.dismiss();
                detailAdapter.remove(position);
                showNumber(-2);
                //TODO
            }
        });
        builder.setView(view);


        return  builder.create() ;
    }


}
