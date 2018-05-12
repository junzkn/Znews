package com.jun.znews.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jun.znews.R;

import java.util.ArrayList;
import java.util.List;


public class DeleteGridViewAdapter extends BaseAdapter{

    private List<String> backreason ;
    private Context context ;

    public DeleteGridViewAdapter(List<String> backreason, Context context) {
        this.backreason = backreason ;
        this.context = context ;

    }

    @Override
    public int getCount() {
        return backreason.size();
    }

    @Override
    public Object getItem(int position) {
        return backreason.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.gridview_item, null);
            TextView delReason = convertView.findViewById(R.id.delReason);
            holder.setText(delReason);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        final TextView delReason = holder.getText() ;
        String text = backreason.get(position) ;
        if(text.contains("_")){
            text = text.split("_")[1] ;
        }
        delReason.setText(text);
        if(position==0){
            delReason.setTextColor(R.color.themeColor);
            delReason.setBackground(context.getResources().getDrawable(R.drawable.delpop_tv_selected_bg));
        }

        return convertView;
    }


    private class ViewHolder {
        private TextView button ;

        public TextView getText() {
            return button;
        }

        public void setText(TextView button) {
            this.button = button;
        }
    }
}
