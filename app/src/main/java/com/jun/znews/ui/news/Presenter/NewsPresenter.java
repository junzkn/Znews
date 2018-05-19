package com.jun.znews.ui.news.Presenter;

import android.content.Context;
import android.util.Log;

import com.jun.znews.bean.NewsDetail;
import com.jun.znews.common.SharedPreferencesConstance;
import com.jun.znews.net.BaseObserver;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.ui.news.Contract.INewsContract;
import com.jun.znews.ui.news.model.NewsModel;
import com.jun.znews.utils.NewsUtil;
import com.jun.znews.utils.SharedPreferencesUtil;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class NewsPresenter extends BasePresenter<INewsContract.INewsView,INewsContract.INewsModel> implements INewsContract.INewsPresenter {


    public NewsPresenter(INewsContract.INewsView mView) {
        super(mView);
    }

    @Override
    protected INewsContract.INewsModel createModel() {
        return new NewsModel();
    }

    @Override
    public void loadData(String id,String action,int num) {
        load(id,action,num)
                .compose(mView.bindToLife())
                .subscribe(new BaseObserver<List<NewsDetail.ItemBean>>() {
                @Override
                public void onSuccess(List<NewsDetail.ItemBean> newsDetails) {
                    mView.showNumber(newsDetails.size());
                    mView.setAdapterData(newsDetails);
                }
                @Override
                public void onFail(Throwable e) {
                    mView.setAdapterData(null);
                }
            }) ;
    }

    @Override
    public void loadUpData(String id, String action, int num) {
        load(id,action,num)
                .compose(mView.bindToLife())
                .subscribe(new BaseObserver<List<NewsDetail.ItemBean>>() {
                    @Override
                    public void onSuccess(List<NewsDetail.ItemBean> newsDetails) {
                        mView.setMoreAdapterData(newsDetails);
                    }
                    @Override
                    public void onFail(Throwable e) {
                        mView.setMoreAdapterData(null);
                    }
                }) ;
    }



    private Observable load(String id,String action,int num){
        Observable<List<NewsDetail>> adapterData = mModel.getData(id,action,num);
        return adapterData
                .map(new Function<List<NewsDetail>, NewsDetail>() {
                    @Override
                    public NewsDetail apply(List<NewsDetail> newsDetails) throws Exception {
                        for(int y=0 ; y<newsDetails.size();y++){
                            for(int i=0 ; i<newsDetails.get(0).getItem().size();i++){
                                //Log.e("net",newsDetails.get(0).getItem().get(i).getType());
                            }
                        }
                        return newsDetails.get(0);
                    }
                })
                .map(new Function<NewsDetail, List<NewsDetail.ItemBean>>() {
                    @Override
                    public List<NewsDetail.ItemBean> apply(@NonNull NewsDetail newsDetail) throws Exception {
                        Iterator<NewsDetail.ItemBean> iterator = newsDetail.getItem().iterator();
                        while (iterator.hasNext()) {
                            try {
                                NewsDetail.ItemBean bean = iterator.next();
                                if (bean.getType().equals(NewsUtil.TYPE_DOC)) {
                                    if (bean.getStyle().getView() != null) {
                                        if (bean.getStyle().getView().equals(NewsUtil.VIEW_TITLEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                        } else {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                        }
                                    }
                                } else if (bean.getType().equals(NewsUtil.TYPE_ADVERT)) {
                                    if (bean.getStyle() != null) {
                                        if (bean.getStyle().getView().equals(NewsUtil.VIEW_TITLEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                        } else if (bean.getStyle().getView().equals(NewsUtil.VIEW_SLIDEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG;
                                        } else {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG;
                                        }
                                    } else {
                                        iterator.remove();
                                    }
                                } else if (bean.getType().equals(NewsUtil.TYPE_SLIDE)) {
                                    if (bean.getLink().getType().equals("doc")) {
                                        if (bean.getStyle().getType().equals(NewsUtil.VIEW_SLIDEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                        } else {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                        }
                                    } else {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_SLIDE;
                                    }
                                } else if (bean.getType().equals(NewsUtil.TYPE_PHVIDEO)) {
                                    bean.itemType = NewsDetail.ItemBean.TYPE_PHVIDEO;
                                } else {
                                    // 凤凰新闻 类型比较多，目前只处理能处理的类型
                                    iterator.remove();
                                }
                            } catch (Exception e) {
                                iterator.remove();
                                e.printStackTrace();
                            }
                        }
                        return newsDetail.getItem();
                    }
                }) ;
    }


}
