package com.jun.znews.ui.setting;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;

import com.jun.znews.R;
import com.jun.znews.common.SharedPreferencesConstance;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.utils.SharedPreferencesUtil;
import com.jun.znews.widget.SettingSwipeView;

import static com.jun.znews.common.SharedPreferencesConstance.NIGHT_MODE;

public class SettingActivity extends BaseActivity {

    SettingSwipeView ss ;
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
        ss = findViewById(R.id.siv_wutu) ;
        spUtil = SharedPreferencesUtil.getInstance(this);
        ss.setCheck(spUtil.getBooleanValue(NIGHT_MODE));
        SwitchCompat viewById = ss.findViewById(R.id.cb_setting_select);
        viewById.setChecked(true);


    }

    @Override
    public void prepare() {
        Log.e("prepare",ss.isCheck()+"");

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ss.setCheck(!ss.isCheck());

            }
        });

    }

    @Override
    public void recreate() {


        super.recreate();

    }
}
