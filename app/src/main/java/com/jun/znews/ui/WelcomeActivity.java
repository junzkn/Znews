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
import com.jun.znews.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {


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
    }

    public void prepare() {

    }



    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();
    }


}
