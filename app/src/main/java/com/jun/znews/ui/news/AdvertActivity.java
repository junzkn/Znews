package com.jun.znews.ui.news;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jun.znews.R;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.news.Presenter.ArticlePresenter;


public class AdvertActivity extends BaseActivity<ArticlePresenter>  {

    private ProgressBar mPbProgress;
    private WebView mWebviewAdvert;
    private ImageView back;

    private static final String URL = "url";

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public ArticlePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_advert;
    }

    @Override
    public void init() {
        mPbProgress = findViewById(R.id.pb_progress);
        mWebviewAdvert = findViewById(R.id.webview_advert);
        back = findViewById(R.id.iv_back);
    }

    @Override
    public void prepare() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertActivity.this.finish();
            }
        });

        getSetting(mWebviewAdvert);
        mWebviewAdvert.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        mWebviewAdvert.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (mPbProgress == null) {
                    return;
                }
                if (newProgress == 100) {
                    mPbProgress.setVisibility(View.GONE);
                } else {
                    mPbProgress.setVisibility(View.VISIBLE);
                    mPbProgress.setProgress(newProgress);
                }
            }
        });


        if (getIntent() == null) return;
        String url = getIntent().getStringExtra(URL);
        if (!TextUtils.isEmpty(url)) {
            mWebviewAdvert.loadUrl(url);
        }
    }


    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, AdvertActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }


    private void getSetting(WebView webview) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setUseWideViewPort(false);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.getSettings().setDomStorageEnabled(true);

    }


}
