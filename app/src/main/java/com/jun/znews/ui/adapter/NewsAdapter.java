package com.jun.znews.ui.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jun.znews.R;
import com.jun.znews.bean.NewsDetail;
import com.jun.znews.utils.ImageLoaderUtil;

import java.util.List;

public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsDetail.ItemBean, BaseViewHolder> {

    private Context context;

    public NewsAdapter(List<NewsDetail.ItemBean> data, Context context) {
        super(data);
        this.context = context;
        addItemType(NewsDetail.ItemBean.TYPE_DOC_TITLEIMG, R.layout.item_detail_doc);
        addItemType(NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG, R.layout.item_detail_doc_slideimg);
        addItemType(NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG, R.layout.item_detail_advert);
        addItemType(NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG, R.layout.item_detail_advert_slideimg);
        addItemType(NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG, R.layout.item_detail_advert_longimage);
        addItemType(NewsDetail.ItemBean.TYPE_SLIDE, R.layout.item_detail_slide);
        addItemType(NewsDetail.ItemBean.TYPE_PHVIDEO, R.layout.item_detail_phvideo);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsDetail.ItemBean bean) {
        switch (baseViewHolder.getItemViewType()) {
            case NewsDetail.ItemBean.TYPE_DOC_TITLEIMG:
                baseViewHolder.setText(R.id.ar_title, bean.getTitle());
                baseViewHolder.setText(R.id.tv_source, bean.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_commentsize), bean.getCommentsall()));
                ImageLoaderUtil.LoadImage(mContext, bean.getThumbnail(), (ImageView) baseViewHolder.getView(R.id.ar_logo));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG:
                baseViewHolder.setText(R.id.ar_title, bean.getTitle());
                baseViewHolder.setText(R.id.tv_source, bean.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_commentsize), bean.getCommentsall()));
                try {
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(0), (ImageView) baseViewHolder.getView(R.id.iv_1));
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(1), (ImageView) baseViewHolder.getView(R.id.iv_2));
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(2), (ImageView) baseViewHolder.getView(R.id.iv_3));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG:
                baseViewHolder.setText(R.id.ar_title, bean.getTitle());
                ImageLoaderUtil.LoadImage(mContext, bean.getThumbnail(), (ImageView) baseViewHolder.getView(R.id.ar_logo));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG:
                baseViewHolder.setText(R.id.ar_title, bean.getTitle());
                try {
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(0), (ImageView) baseViewHolder.getView(R.id.iv_1));
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(1), (ImageView) baseViewHolder.getView(R.id.iv_2));
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(2), (ImageView) baseViewHolder.getView(R.id.iv_3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG:
                baseViewHolder.setText(R.id.ar_title, bean.getTitle());
                ImageLoaderUtil.LoadImage(mContext, bean.getThumbnail(), (ImageView) baseViewHolder.getView(R.id.ar_logo));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_SLIDE:
                baseViewHolder.setText(R.id.ar_title, bean.getTitle());
                baseViewHolder.setText(R.id.tv_source, bean.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_commentsize), bean.getCommentsall()));
                ImageLoaderUtil.LoadImage(mContext, bean.getThumbnail(), (ImageView) baseViewHolder.getView(R.id.ar_logo));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_PHVIDEO:
                baseViewHolder.setText(R.id.ar_title, bean.getTitle());
                baseViewHolder.setText(R.id.tv_source, bean.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_commentsize), bean.getCommentsall()));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                ImageLoaderUtil.LoadImage(mContext, bean.getThumbnail(), (ImageView) baseViewHolder.getView(R.id.ar_logo));
                break;

        }

    }
}
