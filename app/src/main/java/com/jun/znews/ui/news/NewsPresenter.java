package com.jun.znews.ui.news;

import android.util.Log;

import com.jun.znews.entity.NewsDetail;
import com.jun.znews.net.BaseObserver;
import com.jun.znews.net.NewsApiService;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.utils.NewsUtils;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsPresenter extends BasePresenter<PageFragment> implements IPresenter {



    public NewsPresenter(IView newsView) {
        mView = newsView;
        mModel = new NewsModel();
    }

    @Override
    public void loadData() {
        Observable<List<NewsDetail>> adapterData = mModel.getAdapterData();
        adapterData
                .map(new Function<List<NewsDetail>, NewsDetail>() {
                    @Override
                    public NewsDetail apply(List<NewsDetail> newsDetails) throws Exception {
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
                                if (bean.getType().equals(NewsUtils.TYPE_DOC)) {
                                    if (bean.getStyle().getView() != null) {
                                        if (bean.getStyle().getView().equals(NewsUtils.VIEW_TITLEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                        } else {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                        }
                                    }
                                } else if (bean.getType().equals(NewsUtils.TYPE_ADVERT)) {
                                    if (bean.getStyle() != null) {
                                        if (bean.getStyle().getView().equals(NewsUtils.VIEW_TITLEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                        } else if (bean.getStyle().getView().equals(NewsUtils.VIEW_SLIDEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG;
                                        } else {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG;
                                        }
                                    } else {
                                        //bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                        iterator.remove();
                                    }
                                } else if (bean.getType().equals(NewsUtils.TYPE_SLIDE)) {
                                    if (bean.getLink().getType().equals("doc")) {
                                        if (bean.getStyle().getView().equals(NewsUtils.VIEW_SLIDEIMG)) {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                        } else {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                        }
                                    } else {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_SLIDE;
                                    }
                                } else if (bean.getType().equals(NewsUtils.TYPE_PHVIDEO)) {
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
                })
                .subscribe(new BaseObserver<List<NewsDetail.ItemBean>>() {
                    @Override
                    public void onSuccess(List<NewsDetail.ItemBean> newsDetails) {
                        mView.setAdapterData(newsDetails);
                    }
                    @Override
                    public void onFail(Throwable e) {
                    }
                }) ;

    }



}
