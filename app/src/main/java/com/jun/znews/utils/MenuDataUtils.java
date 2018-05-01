package com.jun.znews.utils;

import com.jun.znews.R;
import com.jun.znews.entity.LeftItemMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuDataUtils {
    public static List<LeftItemMenu> getLeftItemMenus(){
        List<LeftItemMenu> menus = new ArrayList<>();
        menus.add(new LeftItemMenu(R.drawable.icon_zhanghaoxinxi,"账号信息"));
        menus.add(new LeftItemMenu(R.drawable.icon_wodeguanzhu,"我的关注"));
        menus.add(new LeftItemMenu(R.drawable.icon_shoucang,"我的收藏"));
        menus.add(new LeftItemMenu(R.drawable.icon_yijianfankui,"意见反馈"));
        menus.add(new LeftItemMenu(R.drawable.icon_shezhi,"设置"));
        return menus ;
    }
}
