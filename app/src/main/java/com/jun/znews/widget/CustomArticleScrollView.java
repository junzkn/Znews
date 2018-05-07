package com.jun.znews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CustomArticleScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener = null;
    public CustomArticleScrollView(Context context) {
        super(context);
    }

    public CustomArticleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomArticleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }





    public interface ScrollViewListener {
        void onScrollChanged(CustomArticleScrollView scrollView, int x, int y, int oldx, int oldy);

    }
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }
    //重写滚动方法
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


}
