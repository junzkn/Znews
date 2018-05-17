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

public class SettingClickView extends RelativeLayout {
    private final String SPACE = "http://schemas.android.com/apk/res-auto" ;
    private String desTitle  ;
    private TextView tv_setting_title;

    public SettingClickView(Context context) {
        this(context,null);
    }

    public SettingClickView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context,R.layout.setting_item2,this) ;
        tv_setting_title = this.findViewById(R.id.tv_setting_title);
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



}
