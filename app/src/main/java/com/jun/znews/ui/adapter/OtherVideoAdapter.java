package com.jun.znews.ui.adapter;



import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jun.znews.R;
import com.jun.znews.bean.NewsOtherVideo;
import com.jun.znews.utils.ImageLoaderUtil;

import java.util.List;

public class OtherVideoAdapter extends BaseQuickAdapter<NewsOtherVideo.GuidRelativeVideoInfoBean, BaseViewHolder> {

    private Context context ;
    public OtherVideoAdapter(Context context , @LayoutRes int layoutResId, @Nullable List<NewsOtherVideo.GuidRelativeVideoInfoBean> data) {
        super(layoutResId, data);
        this.context = context ;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, NewsOtherVideo.GuidRelativeVideoInfoBean item) {
        viewHolder.itemView.setTag(item.getColumnId());
        viewHolder.setText(R.id.tv_source,item.getColumnName())
                .setText(R.id.ar_title,item.getName());
        List<NewsOtherVideo.GuidRelativeVideoInfoBean.FilesBean> files = item.getFiles();
        ImageLoaderUtil.LoadImage(context, files.get(files.size()-1).getMediaUrl(), (ImageView) viewHolder.getView(R.id.ar_logo));
    }


}
