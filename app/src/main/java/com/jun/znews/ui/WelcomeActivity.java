package com.jun.znews.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jun.znews.R;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.widget.CustomVideoView;

public class WelcomeActivity extends BaseActivity {

    private Button btn_splash;
    private CustomVideoView vv_splash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        initData() ;
        setListener() ;
    }

    private void setListener() {
        vv_splash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                openActivity(MainActivity.class);
                WelcomeActivity.this.finish();
            }
        });
        btn_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vv_splash.isPlaying()) {
                    vv_splash.stopPlayback();
                    vv_splash = null ;
                }
                openActivity(MainActivity.class);
                WelcomeActivity.this.finish();
            }
        });
    }

    private void initData() {
        vv_splash.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.kr36));
    }

    private void initView() {
        vv_splash = findViewById(R.id.vv_welcome);
        btn_splash = findViewById(R.id.btn_welcome);
    }


    @Override
    protected void onStart() {
        super.onStart();
        vv_splash.start();
    }
}
