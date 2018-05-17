package com.jun.znews.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jun.znews.R;
import com.jun.znews.widget.SettingSwipeView;

public class SettingActivity extends AppCompatActivity {

    SettingSwipeView ss ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ss = findViewById(R.id.siv_wutu) ;
        ss.setOnSettingSwipeListener(new SettingSwipeView.SettingSwipeListener() {
            @Override
            public void onClick(boolean status) {
            }
        });
    }
}
