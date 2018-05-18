package com.jun.znews.utils;

import android.content.Context;

import com.jun.znews.R;
import com.jun.znews.bean.LeftItemMenu;
import com.jun.znews.common.SharedPreferencesConstance;

import java.util.ArrayList;
import java.util.List;

public class MenuDataUtil {


    public static List<LeftItemMenu> getLeftItemMenus(Context context){
        List<LeftItemMenu> menus = new ArrayList<>();
        menus.add(new LeftItemMenu(R.drawable.icon_shoucang,"我的关注"));
        menus.add(new LeftItemMenu(R.drawable.lishi,"观看历史"));
        if(SharedPreferencesUtil.getInstance(context).getBooleanValue(SharedPreferencesConstance.NIGHT_MODE)){
            menus.add(new LeftItemMenu(R.drawable.taiyang,"日间模式"));
        }else {
            menus.add(new LeftItemMenu(R.drawable.zhuti,"夜间模式"));
        }
        menus.add(new LeftItemMenu(R.drawable.icon_shezhi,"设置"));
        menus.add(new LeftItemMenu(R.drawable.fankui,"反馈"));
        return menus ;
    }




}
