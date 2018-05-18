package com.jun.znews.ui.setting;


import android.view.View;

import com.jun.znews.R;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.utils.SharedPreferencesUtil;
import com.jun.znews.widget.SettingClickView;
import com.jun.znews.widget.SettingSwipeView;
import static com.jun.znews.common.SharedPreferencesConstance.*;

public class SettingActivity extends BaseActivity {

    SettingSwipeView siv_wutu , siv_wifibofang , siv_wifilianxubofang ,siv_guanggao;
    SettingClickView scv_ziti , scv_huancun , scv_gengxin , scv_about;
    SharedPreferencesUtil spUtil ;



    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void init() {
        setStatusBarColor(getResources().getColor(R.color.themeColor),0);

        siv_wutu = findViewById(R.id.siv_wutu) ;
        siv_wifibofang = findViewById(R.id.siv_wifibofang) ;
        siv_wifilianxubofang = findViewById(R.id.siv_wifilianxubofang) ;
        siv_guanggao = findViewById(R.id.siv_guanggao) ;
        scv_ziti = findViewById(R.id.scv_ziti) ;
        scv_huancun = findViewById(R.id.scv_huancun) ;
        scv_gengxin = findViewById(R.id.scv_gengxin) ;
        scv_about = findViewById(R.id.scv_about) ;
        spUtil = SharedPreferencesUtil.getInstance(this);

    }

    @Override
    public void prepare() {
        siv_wutu.setCheck(spUtil.getBooleanValue(SETTING_WUTU));
        siv_wifibofang.setCheck(spUtil.getBooleanValue(SETTING_WIFIBOFANG));
        siv_wifilianxubofang.setCheck(spUtil.getBooleanValue(SETTING_WIFILIANXUbOFANG));
        siv_guanggao.setCheck(spUtil.getBooleanValue(SETTING_GUANGGAO));
        scv_ziti.setContextText(spUtil.getStringValue(SETTING_ZITI));
        scv_gengxin.setContextText(spUtil.getStringValue(SETTING_GENGXIN));


        MyListener listener = new MyListener();
        MyClickListener listener1 = new MyClickListener() ;
        siv_wutu.setOnSettingSwipeListener(listener);
        siv_wifibofang.setOnSettingSwipeListener(listener);
        siv_wifilianxubofang.setOnSettingSwipeListener(listener);
        siv_guanggao.setOnSettingSwipeListener(listener);
        scv_ziti.setOnClickListener(listener1);
        scv_huancun.setOnClickListener(listener1);
        scv_gengxin.setOnClickListener(listener1);
        scv_about.setOnClickListener(listener1);

    }



    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }


    private class MyListener implements SettingSwipeView.SettingSwipeListener {
        @Override
        public void onClick(boolean status) {

        }
    }

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }


}
