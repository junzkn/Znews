package com.jun.znews.ui.base;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.jun.znews.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;


public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements BGASwipeBackHelper.Delegate {

    public T basePresenter;
    protected BGASwipeBackHelper mSwipeBackHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        init();
        basePresenter = initPresent();
        prepare();

    }


    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(false);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }


    /**
     * 是否支持滑动
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }


    @Override
    public void onSwipeBackLayoutCancel() {
    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }



    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }



    public abstract T initPresent();

    public abstract int getLayout();

    public abstract void init();

    public abstract void prepare();





}
