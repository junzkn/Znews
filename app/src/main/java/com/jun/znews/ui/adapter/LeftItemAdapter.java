package com.jun.znews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jun.znews.R;
import com.jun.znews.ThisApp;
import com.jun.znews.bean.LeftItemMenu;
import com.jun.znews.utils.MenuDataUtil;

import java.util.List;

public class LeftItemAdapter extends BaseAdapter {
    private LayoutInflater inflater ;
    private List<LeftItemMenu> itemMenuList ;
    private Context context ;
    public LeftItemAdapter (Context context ){
        inflater = LayoutInflater.from(ThisApp.getInstance());
        this.context = context ;
        this.itemMenuList = MenuDataUtil.getLeftItemMenus(this.context) ;
    }
    @Override
    public int getCount() {
        return itemMenuList==null?0:itemMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder  ;
        if(convertView==null){
            holder = new Holder() ;
            convertView = inflater.inflate(R.layout.item_left_menu_layout,null) ;
            holder.icon = convertView.findViewById(R.id.item_left_view_img);
            holder.title = convertView.findViewById(R.id.item_left_view_title) ;
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.icon.setImageResource(itemMenuList.get(position).getIcon());
        holder.title.setText(itemMenuList.get(position).getTitle());
        return convertView;
    }

    private static class Holder{
        ImageView icon ;
        TextView title ;
    }
}
