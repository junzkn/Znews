package com.jun.znews.ui.setting;


import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jun.znews.R;
import com.jun.znews.common.SharedPreferencesConstance;
import com.jun.znews.ui.AboutActivity;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.utils.SharedPreferencesUtil;
import com.jun.znews.utils.VersionUtil;
import com.jun.znews.widget.FontSizePopupWindow;
import com.jun.znews.widget.SettingClickView;
import com.jun.znews.widget.SettingSwipeView;

import static com.jun.znews.common.SharedPreferencesConstance.*;

public class SettingActivity extends BaseActivity<SettingPresenter> implements ISettingContract.ISettingView {

    SettingSwipeView siv_wutu, siv_wifibofang, siv_wifilianxubofang, siv_guanggao;
    SettingClickView scv_ziti, scv_huancun, scv_gengxin, scv_about;
    FontSizePopupWindow fontSizePopupWindow ;
    SharedPreferencesUtil spUtil;
    ImageView ar_back ;
    Toast toast;


    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public SettingPresenter initPresent() {
        return new SettingPresenter(this);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void init() {
        setStatusBarColor(getResources().getColor(R.color.themeColor), 0);
        siv_wutu = findViewById(R.id.siv_wutu);
        siv_wifibofang = findViewById(R.id.siv_wifibofang);
        siv_wifilianxubofang = findViewById(R.id.siv_wifilianxubofang);
        siv_guanggao = findViewById(R.id.siv_guanggao);
        scv_ziti = findViewById(R.id.scv_ziti);
        scv_huancun = findViewById(R.id.scv_huancun);
        scv_gengxin = findViewById(R.id.scv_gengxin);
        scv_about = findViewById(R.id.scv_about);
        ar_back = findViewById(R.id.ar_back);
        spUtil = SharedPreferencesUtil.getInstance(this);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        fontSizePopupWindow = new FontSizePopupWindow(getApplicationContext(), new itemClick());
    }

    @Override
    public void prepare() {
        siv_wutu.setCheck(spUtil.getBooleanValue(SETTING_WUTU));
        siv_wifibofang.setCheck(spUtil.getBooleanValue(SETTING_WIFIBOFANG));
        siv_wifilianxubofang.setCheck(spUtil.getBooleanValue(SETTING_WIFILIANXUbOFANG));
        siv_guanggao.setCheck(spUtil.getBooleanValue(SETTING_GUANGGAO));
        scv_ziti.setContextText(spUtil.getStringValue(SETTING_ZITI));
        scv_gengxin.setContextText("Ver." + VersionUtil.getLocalVersion(this));
        scv_huancun.setContextText(spUtil.getStringValue(SETTING_HUANCUN));

        MyListener listener = new MyListener();
        MyClickListener listener1 = new MyClickListener();
        siv_wutu.setOnSettingSwipeListener(listener);
        siv_wifibofang.setOnSettingSwipeListener(listener);
        siv_wifilianxubofang.setOnSettingSwipeListener(listener);
        siv_guanggao.setOnSettingSwipeListener(listener);
        scv_ziti.setOnClickListener(listener1);
        scv_huancun.setOnClickListener(listener1);
        scv_gengxin.setOnClickListener(listener1);
        scv_about.setOnClickListener(listener1);
        ar_back.setOnClickListener(listener1);

        basePresenter.getCache();
    }


    @Override
    public void setCache(String s) {
        scv_huancun.setContextText(s);
        spUtil.putStringValue(SETTING_HUANCUN, s);
    }

    @Override
    public void setCacheClear() {
        toast.setText("缓存已清理");
        toast.show();
    }

    @Override
    public void setUpdate(Boolean b) {
        if (b) {

        } else {
            toast.setText("已是最新版本");
            toast.show();
        }
    }


    private class MyListener implements SettingSwipeView.SettingSwipeListener {
        @Override
        public void onClick(boolean status, View view) {
            switch (view.getId()) {
                case R.id.siv_wutu:
                    spUtil.putBooleanValue(SETTING_WUTU, status);
                    break;
                case R.id.siv_wifibofang:
                    spUtil.putBooleanValue(SETTING_WIFIBOFANG, status);
                    break;
                case R.id.siv_wifilianxubofang:
                    spUtil.putBooleanValue(SETTING_WIFILIANXUbOFANG, status);
                    break;
                case R.id.siv_guanggao:
                    spUtil.putBooleanValue(SETTING_GUANGGAO, status);
                    break;
            }
        }
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ar_back:
                    finish();
                    break ;
                case R.id.scv_ziti:
                    fontSizePopupWindow.showAtLocation(findViewById(R.id.setting_layout), Gravity.BOTTOM, 0, 0);
                    break;
                case R.id.scv_huancun:
                    basePresenter.clearCache();
                    break;
                case R.id.scv_gengxin:
                    basePresenter.checkUpdate();
                    break;
                case R.id.scv_about:
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                    break;

            }
        }
    }


    private class itemClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.font_size_big:
                    spUtil.putStringValue(SharedPreferencesConstance.SETTING_ZITI,"大");
                    scv_ziti.setContextText("大");
                    break;
                case R.id.font_size_middle:
                    spUtil.putStringValue(SharedPreferencesConstance.SETTING_ZITI,"中");
                    scv_ziti.setContextText("中");
                    break;
                case R.id.font_size_small:
                    spUtil.putStringValue(SharedPreferencesConstance.SETTING_ZITI,"小");
                    scv_ziti.setContextText("小");
                    break;
            }
            fontSizePopupWindow.dismiss();
        }
    }
}
