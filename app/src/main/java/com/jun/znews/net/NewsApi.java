package com.jun.znews.net;

import android.support.annotation.StringDef;
import android.util.Log;

import com.jun.znews.bean.NewsArticleBean;
import com.jun.znews.bean.NewsDetail;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;


public class NewsApi {

    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";

    @StringDef({ACTION_DEFAULT,ACTION_DOWN,ACTION_UP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions{

    }

    public static NewsApi sInstance;

    private NewsApiService mService;

    public NewsApi(NewsApiService newsApiService) {
        this.mService = newsApiService;
    }

    public static NewsApi getInstance(NewsApiService newsApiService) {
        if (sInstance == null)
            sInstance = new NewsApi(newsApiService);
        return sInstance;
    }


    public Observable<List<NewsDetail>> getNewsDetail(String id, @Actions String action, int pullNum) {
        Log.e("ee","1");
        return mService.getNewsDetail(id, action, pullNum);
    }


    public Observable<NewsArticleBean> getNewsArticle(String aid){
        Log.e("ee","2");

        if (aid.startsWith("sub")){
            return mService.getNewsArticleWithSub(aid);
        }else {
            return mService.getNewsArticleWithCmpp(ApiConstants.sGetNewsArticleCmppApi + ApiConstants.sGetNewsArticleDocCmppApi,aid);
        }
    }


}
