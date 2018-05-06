package com.jun.znews.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jun.znews.R;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.widget.CustomVideoView;

public class WelcomeActivity extends AppCompatActivity {

    private Button btn_splash;
    private CustomVideoView vv_splash;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_welcome);
        init();
        prepare();
    }


    public void init() {
        vv_splash = findViewById(R.id.vv_welcome);
        btn_splash = findViewById(R.id.btn_welcome);
    }

    public void prepare() {
        vv_splash.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.kr36));
        vv_splash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                openMainActivity();
            }
        });
        btn_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vv_splash.isPlaying()) {
                    vv_splash.stopPlayback();
                    vv_splash = null;
                }
                openMainActivity();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        vv_splash.start();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();

    }


}
