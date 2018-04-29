package com.jun.znews.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainDragLayout extends FrameLayout {

    private boolean isShowShadow = true ;
    //手势处理类
    private GestureDetectorCompat gestureDetector;
    //视图拖拽移动帮助类
    private ViewDragHelper dragHelper;
    //滑动监听器
    private DragListener dragListener;
    //水平拖拽的距离
    private int range;
    //宽度
    private int width;
    //高度
    private int height;
    //main视图距离在ViewGroup距离左边的距离
    private int mainLeft;
    private Context context;
    private ImageView iv_shadow;
    //左侧布局
    private RelativeLayout vg_left;
    //右侧(主界面布局)
    private CustomRelativeLayout vg_main;
    //页面状态 默认为关闭
    private Status status = Status.Close;

    //页面状态(滑动,打开,关闭)
    public enum Status {
        Drag, Open, Close
    }

    public MainDragLayout(@NonNull Context context) {
        super(context);
    }


    public MainDragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
