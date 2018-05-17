package com.jun.znews.ui.news;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jun.video.JZVideoPlayer;
import com.jun.video.JZVideoPlayerStandard;
import com.jun.znews.R;
import com.jun.znews.bean.NewsOtherVideo;
import com.jun.znews.bean.NewsVideo;
import com.jun.znews.ui.adapter.OtherVideoAdapter;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.news.Contract.IVideoContract;
import com.jun.znews.ui.news.Presenter.VideoPresenter;
import com.jun.znews.utils.ImageLoaderUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class VideoActivity extends BaseActivity<VideoPresenter> implements IVideoContract.IVideoView {

    private String guid;
    private RecyclerView rl;
    private JZVideoPlayerStandard videoPlayer;
    private OtherVideoAdapter otherVideoAdapter;
    private LinearLayoutManager linearLayoutManager;
    List<NewsOtherVideo.GuidRelativeVideoInfoBean> beanList;
    private int lastPosition = 0;
    private String tag = "";
    View lastView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        guid = getIntent().getStringExtra("guid");
        super.onCreate(savedInstanceState);
    }


    @Override
    public VideoPresenter initPresent() {
        return new VideoPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void init() {
        rl = findViewById(R.id.rl_video);
        videoPlayer = findViewById(R.id.video);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        beanList = new ArrayList<>();
        otherVideoAdapter = new OtherVideoAdapter(getApplicationContext(), R.layout.item_other_video, beanList);
    }

    @Override
    public void prepare() {
        rl.setAdapter(otherVideoAdapter);
        rl.setHasFixedSize(true);
        rl.setLayoutManager(linearLayoutManager);

        //otherVideoAdapter.setHeaderView() ;
        otherVideoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (lastPosition == position) return;
                if (lastView != null) {
                    lastView.findViewById(R.id.iv_isPlay).setVisibility(View.VISIBLE);
                    lastView.findViewById(R.id.tv_show).setVisibility(View.GONE);
                }
                NewsOtherVideo.GuidRelativeVideoInfoBean item = otherVideoAdapter.getItem(position);
                view.findViewById(R.id.iv_isPlay).setVisibility(View.GONE);
                view.findViewById(R.id.tv_show).setVisibility(View.VISIBLE);
                lastView = view;
                lastPosition = position;


                playGuid(item.getGuid());
            }
        });
        basePresenter.loadData(guid);
        basePresenter.loadOtherData(guid);

    }

    private void playGuid(String guid) {
        basePresenter.loadData(guid);
    }

    @Override
    public void setData(NewsVideo.VideoBean videoBean) {
        if(videoBean!=null){
            String urlH = videoBean.getVideoURLHigh();
            String urlM = videoBean.getVideoURLMid();
            String urlL = videoBean.getVideoURLLow();
            LinkedHashMap<String, String> map = new LinkedHashMap();
            map.put("高清", urlH);
            map.put("标清", urlL);
            map.put("普清", urlM);
            Object[] dataSourceObjects = new Object[1];
            dataSourceObjects[0] = map;
            videoPlayer.setUp(dataSourceObjects, 0, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoBean.getTitle());
            ImageLoaderUtil.LoadImage(getApplicationContext(), videoBean.getThumbnailVertical(), (ImageView) videoPlayer.thumbImageView);
            videoPlayer.startVideo();
        }

    }

    @Override
    public void setOtherData(List<NewsOtherVideo.GuidRelativeVideoInfoBean> data) {
        if(data!=null){
            List<NewsOtherVideo.GuidRelativeVideoInfoBean> n = new ArrayList<>() ;
            for(int i=0 ; i<8 && i<data.size() ; i++){
                n.add(data.get(i)) ;
            }
            otherVideoAdapter.setNewData(n);
        }

    }

    @Override
    public LifecycleTransformer bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


}
