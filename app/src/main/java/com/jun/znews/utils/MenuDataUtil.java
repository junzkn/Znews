package com.jun.znews.utils;

import com.jun.znews.R;
import com.jun.znews.bean.LeftItemMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuDataUtil {
    public static List<LeftItemMenu> getLeftItemMenus(){
        List<LeftItemMenu> menus = new ArrayList<>();
        menus.add(new LeftItemMenu(R.drawable.icon_shoucang,"我的关注"));
        menus.add(new LeftItemMenu(R.drawable.lishi,"观看历史"));
        menus.add(new LeftItemMenu(R.drawable.zhuti,"夜间模式"));
        menus.add(new LeftItemMenu(R.drawable.icon_shezhi,"设置"));
        menus.add(new LeftItemMenu(R.drawable.fankui,"反馈"));
        return menus ;
    }
}
