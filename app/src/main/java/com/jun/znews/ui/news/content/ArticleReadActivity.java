package com.jun.znews.ui.news.content;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.jun.znews.R;
import com.jun.znews.entity.NewsArticleBean;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.utils.DateUtil;
import com.jun.znews.widget.CustomArticleScrollView;
import com.trello.rxlifecycle2.LifecycleTransformer;

public class ArticleReadActivity extends BaseActivity<ArticleReadPresenter> implements  IArticleReadView{

    private ImageView ar_back , ar_topLogo , ar_logo ;
    private Button ar_topLike , ar_like ;
    private RelativeLayout ar_topBar , ar_bar ;
    private CustomArticleScrollView ar_scrollView ;
    private TextView ar_title ,ar_name , ar_topName , ar_updateTime , ar_topUpdateTime ;
    private WebView ar_webView ;
    protected Button loadFail ;
    protected TextView  loadingData ;
    private String aid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aid = getIntent().getStringExtra("aid");
        Log.e("jun",aid);
    }

    @Override
    public ArticleReadPresenter initPresent() {
        return new ArticleReadPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_article;
    }

    @Override
    public void init() {
        loadingData = findViewById(R.id.img_loadingData) ;
        loadFail = findViewById(R.id.img_loadFail) ;
        ar_back = findViewById(R.id.ar_back);
        ar_topLogo = findViewById(R.id.ar_topLogo);
        ar_logo = findViewById(R.id.ar_logo);
        ar_topLike = findViewById(R.id.ar_topLike);
        ar_like = findViewById(R.id.ar_like);
        ar_topBar = findViewById(R.id.ar_topBar);
        ar_bar = findViewById(R.id.ar_bar);
        ar_scrollView = findViewById(R.id.ar_scrollView);
        ar_title = findViewById(R.id.ar_title);
        ar_name = findViewById(R.id.ar_name);
        ar_topName = findViewById(R.id.ar_topName);
        ar_updateTime = findViewById(R.id.ar_updateTime);
        ar_topUpdateTime = findViewById(R.id.ar_topUpdateTime);
        ar_webView = findViewById(R.id.ar_webView);

    }

    @Override
    public void prepare() {
        ar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleReadActivity.this.finish();
            }
        });
        setWebViewSetting() ;
        ar_scrollView.setScrollViewListener(new CustomArticleScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(CustomArticleScrollView scrollView, int x, int scrollY, int oldx, int oldy) {
                if (scrollY > ar_bar.getHeight()) {
                    ar_topBar.setVisibility(View.VISIBLE);
                } else {
                    ar_topBar.setVisibility(View.GONE);

                }
            }
        });
        loadFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                basePresenter.loadData(aid);
            }
        });

    }
    private void setWebViewSetting() {
        addjs(ar_webView);
        ar_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        ar_webView.getSettings().setJavaScriptEnabled(true);
        ar_webView.getSettings().setAppCacheEnabled(true);
        ar_webView.getSettings().setAllowFileAccessFromFileURLs(true);
        ar_webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ar_webView.setVerticalScrollBarEnabled(false);
        ar_webView.setVerticalScrollbarOverlay(false);
        ar_webView.setHorizontalScrollBarEnabled(false);
        ar_webView.setHorizontalScrollbarOverlay(false);
        ar_webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        ar_webView.getSettings().setDomStorageEnabled(true);
        ar_webView.loadUrl("file:///android_asset/ifeng/post_detail.html");
        ar_webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                basePresenter.loadData(aid);
            }
        });
    }

    private void addjs(WebView ar_webView) {
        class JsObject {
            @JavascriptInterface
            public void jsFunctionimg(final String i) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("zkn", "run: " + i);
                    }
                });

            }

        }
        ar_webView.addJavascriptInterface(new JsObject(), "jscontrolimg");

    }

    @Override
    public void setData(final NewsArticleBean articleBean) {
        if(articleBean==null) {
            loadFail();
            return ;
        }
        loadSucceed();
        ar_bar.setVisibility(View.VISIBLE);
        ar_title.setText(articleBean.getBody().getTitle());
        ar_updateTime.setText(DateUtil.getTimestampString(DateUtil.string2Date(articleBean.getBody().getUpdateTime(), "yyyy/MM/dd HH:mm:ss")));
        if (articleBean.getBody().getSubscribe() != null) {
            Glide.with(this).load(articleBean.getBody().getSubscribe().getLogo())
                    .apply(new RequestOptions()
                            .transform(new CircleCrop())
                            //.placeholder()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ar_logo);
            Glide.with(this).load(articleBean.getBody().getSubscribe().getLogo())
                    .apply(new RequestOptions()
                            .transform(new CircleCrop())
                            //.placeholder()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ar_topLogo);
            ar_name.setText(articleBean.getBody().getSubscribe().getCateSource());
            ar_topName.setText(articleBean.getBody().getSubscribe().getCateSource());
            ar_topUpdateTime.setText(articleBean.getBody().getSubscribe().getCatename());
        }else {
            ar_topName.setText(articleBean.getBody().getSource());
            ar_name.setText(articleBean.getBody().getSource());
            ar_topUpdateTime.setText(!TextUtils.isEmpty(articleBean.getBody().getAuthor()) ? articleBean.getBody().getAuthor() : articleBean.getBody().getEditorcode());
        }

        ar_webView.post(new Runnable() {
            @Override
            public void run() {
                final String content = articleBean.getBody().getText();
                String url = "javascript:show_content(\'" + content + "\')";
                ar_webView.loadUrl(url);
            }
        });
    }

    @Override
    public LifecycleTransformer bindToLife() {
        return this.bindToLifecycle();
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

}
