package com.jun.znews.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jun.znews.R;
import com.jun.znews.entity.NewsDetail;
import com.jun.znews.utils.ImageLoaderUtil;

import java.util.List;

public class PageRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int type = 1 ;
    private Context context ;
    private LayoutInflater inflater ;
    //数据
    private List<NewsDetail.ItemBean> itemBeans ;

    public void setItemBeans(List<NewsDetail.ItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    public PageRecycleAdapter(Context context){
        this.context = context ;
        this.inflater = LayoutInflater.from(this.context) ;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(type==1){
            type--;
            View itemView = inflater.inflate(R.layout.item_home_news,parent,false);
            ItemViewHolder itemViewHolder = new ItemViewHolder(itemView);
            return itemViewHolder ;
        }else {
            type++;
            View adView = inflater.inflate(R.layout.item_home_ad,parent,false);
            ADViewHolder adViewHolder = new ADViewHolder(adView);
            return adViewHolder ;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsDetail.ItemBean itemBean = itemBeans.get(position);
        if(holder instanceof  ItemViewHolder){
            ((ItemViewHolder) holder).item_news_title.setText(itemBean.getTitle());
            ImageLoaderUtil.LoadImage(context, itemBean.getThumbnail(), ((ItemViewHolder) holder).item_news_img);
        }else if (holder instanceof ADViewHolder){
            ((ADViewHolder) holder).item_ad_title.setText(itemBean.getTitle());
            ImageLoaderUtil.LoadImage(context, itemBean.getThumbnail(), ((ADViewHolder) holder).item_ad_img);
        }
    }

    @Override
    public int getItemCount() {
        return itemBeans==null?0:itemBeans.size() ;
    }


    //
    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_news_img ;
        private TextView item_news_title ;
        public ItemViewHolder(View itemView) {
            super(itemView);
            item_news_img = itemView.findViewById(R.id.item_news_img);
            item_news_title = itemView.findViewById(R.id.item_news_title);
        }
    }

    private class ADViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_ad_img ;
        private TextView item_ad_title ;
        public ADViewHolder(View itemView) {
            super(itemView);
            item_ad_img = itemView.findViewById(R.id.item_ad_img);
            item_ad_title = itemView.findViewById(R.id.item_ad_title);
        }
    }

}
