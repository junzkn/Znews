package com.jun.znews.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jun.znews.R;
import com.jun.znews.bean.LeftItemMenu;
import com.jun.znews.bean.Weather;
import com.jun.znews.common.*;
import com.jun.znews.ui.adapter.LeftItemAdapter;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.setting.SettingActivity;
import com.jun.znews.ui.suggest.SuggestActivity;
import com.jun.znews.ui.user.LoginActivity;
import com.jun.znews.ui.user.UserInfoActivity;
import com.jun.znews.utils.SharedPreferencesUtil;
import com.jun.znews.widget.MainDragLayout;
import com.nineoldandroids.view.ViewHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.jun.znews.common.SharedPreferencesConstance.NIGHT_MODE;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.IMainView {

    private MainDragLayout drag_layout;
    private ImageView top_bar_icon;
    private ListView lv_left_main;
    private RelativeLayout topbar;
    private ImageView iv_bottom;
    private Button top_bar_search;
    private TextView iv_text;
    private TextView location, wendu;
    private ImageView weather;
    LeftItemAdapter leftItemAdapter ;
    private SharedPreferencesUtil spUtil;
    String[] MY_PERMISSION = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return true;
        }
    });


    @Override
    public MainPresenter initPresent() {
        return new MainPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void init() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        getWindow().setStatusBarColor(android.R.color.transparent);
        ActivityCompat.requestPermissions(this, MY_PERMISSION, 1);
        topbar = findViewById(R.id.topbar);
        drag_layout = findViewById(R.id.drag_layout);
        top_bar_icon = findViewById(R.id.top_bar_icon);
        lv_left_main = findViewById(R.id.lv_left_main);
        iv_bottom = findViewById(R.id.iv_bottom);
        iv_text = findViewById(R.id.iv_text);
        top_bar_search = findViewById(R.id.top_bar_search);
        weather = findViewById(R.id.weather);
        location = findViewById(R.id.location);
        wendu = findViewById(R.id.wendu);
        spUtil = SharedPreferencesUtil.getInstance(this);


        leftItemAdapter = new LeftItemAdapter(this);
    }

    @Override
    public void prepare() {

        lv_left_main.setAdapter(leftItemAdapter);
        lv_left_main.setOnItemClickListener(new CustomOnItemClickListener());
        drag_layout.setDragListener(new CustomDragListener());
        CustomOnClickListener customOnClickListener = new CustomOnClickListener();
        top_bar_icon.setOnClickListener(customOnClickListener);
        iv_bottom.setOnClickListener(customOnClickListener);
        iv_text.setOnClickListener(customOnClickListener);
        top_bar_search.setOnClickListener(customOnClickListener);
        basePresenter.getWeather();

        top_bar_icon.setImageResource(R.mipmap.ic_launcher);
        iv_bottom.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public void setWeather(Weather.HeWeather6Bean bean) {
        if (bean != null) {
            String loca = bean.getBasic().getLocation();
            String cond_code = bean.getNow().getCond_code();
            String tmp = bean.getNow().getTmp();
            spUtil.putStringValue(SharedPreferencesConstance.WEATHER_LOCA, loca);
            spUtil.putStringValue(SharedPreferencesConstance.WEATHER_TMP, tmp);
            spUtil.putStringValue(SharedPreferencesConstance.WEATHER_COND_CODE, cond_code);
            location.setText(loca);
            wendu.setText(tmp + "°C");
            try {
                InputStream is = getAssets().open("cond_icon_heweather/" + cond_code + ".png");
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                weather.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String loca = spUtil.getStringValue(SharedPreferencesConstance.WEATHER_LOCA);
            String tmp = spUtil.getStringValue(SharedPreferencesConstance.WEATHER_TMP);
            String cond_code = spUtil.getStringValue(SharedPreferencesConstance.WEATHER_COND_CODE);
            location.setText(loca);
            wendu.setText(tmp + "°C");
            try {
                InputStream is = getAssets().open("cond_icon_heweather/" + cond_code + ".png");
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                weather.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
            switch (v.getId()) {
                case R.id.iv_bottom:
                case R.id.iv_text:
                    boolean isLogin = spUtil.getBooleanValue(SharedPreferencesConstance.IS_LOGIN);
                    if (isLogin) {
                        Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
                        startActivityForResult(intent, 100);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivityForResult(intent, 101);
                    }
                    break;
                case R.id.top_bar_icon:
                    drag_layout.open();
                    break;
                case R.id.top_bar_search:
                    //TODO
                    break;
            }

        }
    }

    private class CustomOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    drag_layout.close();
                    boolean booleanValue = spUtil.getBooleanValue(NIGHT_MODE);
                    AppCompatDelegate.setDefaultNightMode(booleanValue?AppCompatDelegate.MODE_NIGHT_NO:AppCompatDelegate.MODE_NIGHT_YES);
                    spUtil.putBooleanValue(NIGHT_MODE, !booleanValue);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recreate();
                        }
                    }, 500);
                    break;
                case 3:
                    Intent intent3 = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(intent3);
                    break;
                case 4:
                    Intent intent4 = new Intent(getApplicationContext(), SuggestActivity.class);
                    startActivity(intent4);
                    break;
                case 5:
                    break;
            }
        }
    }


    public RelativeLayout getTopbar() {
        return topbar;
    }

    public MainDragLayout getDrag_layout() {
        return drag_layout;
    }


}
