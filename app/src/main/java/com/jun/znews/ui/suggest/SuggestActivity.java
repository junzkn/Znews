package com.jun.znews.ui.suggest;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jun.znews.R;
import com.jun.znews.ui.adapter.SuggestTypeListAdapter;
import com.jun.znews.ui.base.BaseActivity;
import com.jun.znews.ui.base.BasePresenter;

import java.util.HashMap;

public class SuggestActivity extends BaseActivity {
    private ImageView top_bar_linear_back; // 返回
    private LinearLayout layout_type;
    private ImageView img_type;
    private TextView tv_type;
    private EditText et_suggest;// 建议内容
    private EditText et_phone;// 联系方式
    private Button bt_confirm;// 提交
    private String[] types;
    private String type;
    private PopupWindow typeWindow;
    Toast toast ;
    private ProgressDialog pDialog;
    private HashMap<String, String> params;

    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_suggest;
    }

    @Override
    public void init() {
        setStatusBarColor(getResources().getColor(R.color.themeColor),0);
        toast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        top_bar_linear_back= this.findViewById(R.id.ar_back);
        layout_type= this.findViewById(R.id.linear_question_classify);
        img_type= this.findViewById(R.id.img_suggest_type);
        tv_type= this.findViewById(R.id.tv_suggest_type);
        et_suggest= this.findViewById(R.id.et_suggest_content);
        et_phone= this.findViewById(R.id.et_suggest_phone);
        bt_confirm= this.findViewById(R.id.btn_confirm);
    }

    @Override
    public void prepare() {
        types = getResources().getStringArray(R.array.array_suggest_type);
        top_bar_linear_back.setOnClickListener(new MyOnClickListener());
        bt_confirm.setOnClickListener(new MyOnClickListener());
        layout_type.setOnClickListener(new MyOnClickListener());
    }


    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ar_back:
                    SuggestActivity.this.finish();
                    break;
                case R.id.linear_question_classify:
                    if (typeWindow == null) {
                        typeWindow = new PopupWindow(SuggestActivity.this);
                        typeWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        typeWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                        View contentView = LayoutInflater.from(SuggestActivity.this).inflate(
                                R.layout.suggest_type_window_layout, null);
                        ListView lv_type = contentView
                                .findViewById(R.id.lv_suggest_type_window);
                        SuggestTypeListAdapter adapter = new SuggestTypeListAdapter(
                                SuggestActivity.this, types);
                        lv_type.setAdapter(adapter);
                        lv_type.setOnItemClickListener(new MyOnItemClickListener());
                        typeWindow.setContentView(contentView);
                        typeWindow.setFocusable(true);
                        typeWindow.setOutsideTouchable(true);
                        typeWindow.setBackgroundDrawable(new ColorDrawable(0));
                        typeWindow.showAsDropDown(tv_type);
                    } else {
                        if (typeWindow.isShowing()) {
                            typeWindow.dismiss();
                        } else {
                            typeWindow.showAsDropDown(tv_type);
                        }
                    }
                    break;
                case R.id.btn_confirm:
                    String suggestStr = et_suggest.getText().toString();
                    String contractStr = et_phone.getText().toString();
                    if (suggestStr.equals("")) {
                        toast.setText("请填写建议内容");
                        toast.show();
                        return;
                    }
                    if (contractStr.equals("")) {
                        toast.setText("请填写联系方式");
                        toast.show();
                        return;
                    }

                    //开始提交数据到服务器


                    break;
            }
        }
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            tv_type.setText(types[(int) id]);
            type = types[(int) id];
            if (typeWindow != null) {
                typeWindow.dismiss();
            }
        }
    }


    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    protected void onDestroy() {
        if(toast!=null)toast.cancel();
        super.onDestroy();
    }
}
