package com.jun.znews.ui.news;

import com.jun.znews.entity.NewsDetail;

import java.util.List;

public interface IView {

    void setAdapterData(List<NewsDetail.ItemBean> newsDetails);

    void setMoreAdapterData(List<NewsDetail> newsDetails);


}
