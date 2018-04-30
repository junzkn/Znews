package com.jun.znews.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.BadParcelableException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jun.znews.R;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.widget.CustomVideoView;

public class SplashActivity extends BaseActivity {

    private Button btn_splash;
    private CustomVideoView vv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        vv_splash = findViewById(R.id.vv_splash);
        btn_splash = findViewById(R.id.btn_splash);
        vv_splash.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.kr36));
        vv_splash.start();
        vv_splash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vv_splash.start();
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
                SplashActivity.this.finish();
            }
        });

    }
}
