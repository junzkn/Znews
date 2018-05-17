package com.jun.znews.widget;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jun.znews.R;


/**
 * Created by zkn on 2018/3/14.
 */

public class SettingSwipeView extends RelativeLayout {
    private final String SPACE = "http://schemas.android.com/apk/res-auto" ;
    private String desTitle  ;
    private TextView tv_setting_title;
    private SwitchCompat cb_setting_select;
    private SettingSwipeListener LLListener;


    public SettingSwipeView(Context context) {
        this(context,null);
    }

    public SettingSwipeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingSwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context,R.layout.setting_item,this) ;
        tv_setting_title = this.findViewById(R.id.tv_setting_title);
        cb_setting_select = this.findViewById(R.id.cb_setting_select);
        initAttrs(attrs) ;
        tv_setting_title.setText(desTitle);
    }

    /**
     * 获取属性值
     * @param attrs
     */
    private void initAttrs(AttributeSet attrs) {
        desTitle = attrs.getAttributeValue(SPACE,"desTitle") ;
    }

    public interface SettingSwipeListener {
        void onClick(boolean status) ;
    }


    public boolean isCheck(){
        return cb_setting_select.isChecked() ;
    }

    public void setCheck(boolean isCheck){
        cb_setting_select.setChecked(isCheck);
    }


    public void setOnSettingSwipeListener(SettingSwipeListener settingSwipeListener){
        this.LLListener = settingSwipeListener;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheck(!isCheck());
                if(LLListener!=null){
                    LLListener.onClick(isCheck());
                }
            }
        });
    }

}
