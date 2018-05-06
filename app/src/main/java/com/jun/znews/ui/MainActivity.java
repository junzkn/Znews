package com.jun.znews.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.jun.znews.R;
import com.jun.znews.ui.adapter.LeftItemAdapter;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.widget.MainDragLayout;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends AppCompatActivity {

    private MainDragLayout drag_layout;
    private ImageView top_bar_icon;
    private ListView lv_left_main;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(android.R.color.transparent);
        getWindow().setNavigationBarColor(android.R.color.transparent);
        init();
        prepare();
    }


    private void init() {
        drag_layout = findViewById(R.id.drag_layout);
        top_bar_icon = findViewById(R.id.top_bar_icon);
        lv_left_main = findViewById(R.id.lv_left_main);
    }

    private void prepare() {
        lv_left_main.setAdapter(new LeftItemAdapter());
        drag_layout.setDragListener(new CustomDragListener());
        top_bar_icon.setOnClickListener(new CustomOnClickListener());
    }

    private class CustomDragListener implements MainDragLayout.DragListener {
        @Override
        public void onOpen() {
        }

        @Override
        public void onClose() {
        }

        @Override
        public void onDrag(float percent) {
            ViewHelper.setAlpha(top_bar_icon, 1.1f - percent);
        }
    }

    private class CustomOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            drag_layout.open();
        }
    }

    public MainDragLayout getDrag_layout() {
        return drag_layout;
    }
}
