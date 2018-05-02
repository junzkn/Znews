package com.jun.znews.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.jun.znews.R;
import com.jun.znews.adapter.LeftItemAdapter;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.widget.MainDragLayout;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends BaseActivity {

    private MainDragLayout drag_layout;
    private ImageView top_bar_icon;
    private ListView lv_left_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView() ;
        initdata() ;
        initListener();
    }

    private void initdata() {
        lv_left_main.setAdapter(new LeftItemAdapter());
    }


    private void initView() {
        drag_layout = findViewById(R.id.drag_layout) ;
        top_bar_icon =findViewById(R.id.top_bar_icon);
        lv_left_main = findViewById(R.id.lv_left_main);
    }
    public void initListener() {
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
            ViewHelper.setAlpha(top_bar_icon,1-percent);
        }
    }

    private class CustomOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            drag_layout.open();
        }
    }

    public MainDragLayout getDrag_layout(){
        return drag_layout ;
    }
}
