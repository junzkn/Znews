package com.jun.znews.ui.user;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jun.znews.R;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.base.BasePresenter;

public class LoginActivity extends BaseActivity {

    private RelativeLayout rl;
    private ImageView img_back;
    private TextView tv_loginTitle, tv_type, tv_forget;
    private EditText et_phone, et_phoneOrMail, et_password;
    private Button tv_login , tv_getNumber;
    InputMethodManager imm;
    private boolean loginType = false;


    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        setStatusBarColor(getResources().getColor(R.color.themeColor),0);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        rl = findViewById(R.id.rl);
        img_back = findViewById(R.id.ar_back);
        tv_loginTitle = findViewById(R.id.login_title);
        tv_type = findViewById(R.id.type);
        tv_forget = findViewById(R.id.forget);
        tv_login = findViewById(R.id.login);
        tv_getNumber = findViewById(R.id.getNumber);
        et_phone = findViewById(R.id.phone);
        et_phoneOrMail = findViewById(R.id.phoneOrMail);
        et_password = findViewById(R.id.password);
    }


    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ar_back:
                    finish();
                    break;
                case R.id.type:
                    if(loginType==false){
                        tv_getNumber.setVisibility(View.GONE);
                        et_phone.setVisibility(View.GONE);
                        et_phoneOrMail.setVisibility(View.VISIBLE);
                        et_password.setVisibility(View.VISIBLE);
                        tv_login.setVisibility(View.VISIBLE);
                        tv_forget.setVisibility(View.VISIBLE);
                        tv_loginTitle.setText("登 录");
                        loginType=true ;
                    }else {
                        loginType=false ;
                        tv_getNumber.setVisibility(View.VISIBLE);
                        et_phone.setVisibility(View.VISIBLE);
                        et_phoneOrMail.setVisibility(View.GONE);
                        et_password.setVisibility(View.GONE);
                        tv_login.setVisibility(View.GONE);
                        tv_forget.setVisibility(View.GONE);
                        tv_loginTitle.setText("登 录/注 册");
                    }
                    break;
                case R.id.forget:
                    break;
                case R.id.login:
                    break;
                case R.id.getNumber:
                    break;

            }
        }
    }

    private class MyFocusListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.password:
                    if (!hasFocus)
                        imm.hideSoftInputFromWindow(rl.getWindowToken(), 0);
                    if (!hasFocus && TextUtils.isEmpty(et_password.getText().toString()))
                        et_password.setText("密 码");
                    if (hasFocus && et_password.getText().toString().equals("密 码"))
                        et_password.setText("");
                    break;
                case R.id.phone:
                    if (!hasFocus)
                        imm.hideSoftInputFromWindow(rl.getWindowToken(), 0);
                    if (!hasFocus && TextUtils.isEmpty(et_phone.getText().toString()))
                        et_phone.setText("点击输入手机号");
                    if (hasFocus && et_phone.getText().toString().equals("点击输入手机号"))
                        et_phone.setText("");
                    break;
                case R.id.phoneOrMail:
                    if (!hasFocus)
                        imm.hideSoftInputFromWindow(rl.getWindowToken(), 0);
                    if (!hasFocus && TextUtils.isEmpty(et_phoneOrMail.getText().toString()))
                        et_phoneOrMail.setText("手机号/邮箱账号");
                    if (hasFocus && et_phoneOrMail.getText().toString().equals("手机号/邮箱账号"))
                        et_phoneOrMail.setText("");
                    break;
            }

        }
    }


    @Override
    public void prepare() {
        rl.requestFocus();
        MyFocusListener myFocusListener = new MyFocusListener();
        MyClickListener myClickListener = new MyClickListener();
        et_password.setOnFocusChangeListener(myFocusListener);
        et_phoneOrMail.setOnFocusChangeListener(myFocusListener);
        et_phone.setOnFocusChangeListener(myFocusListener);
        img_back.setOnClickListener(myClickListener);
        tv_loginTitle.setOnClickListener(myClickListener);
        tv_type.setOnClickListener(myClickListener);
        tv_forget.setOnClickListener(myClickListener);
        tv_login.setOnClickListener(myClickListener);
        tv_getNumber.setOnClickListener(myClickListener);
        rl.setOnClickListener(myClickListener);
    }


    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }
}
