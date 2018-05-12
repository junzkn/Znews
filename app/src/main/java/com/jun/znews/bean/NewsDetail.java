package com.jun.znews.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class NewsDetail implements Serializable {

    @Override
    public String toString() {
        return "NewsDetail{" +
                "listId='" + listId + '\'' +
                ", type='" + type + '\'' +
                ", expiredTime=" + expiredTime +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", topsize=" + topsize +
                ", item=" + item +
                '}';
    }


    private String listId;
    private String type;
    private int expiredTime;
    private int currentPage;
    private int totalPage;
    private int topsize;
    private String downHideFocusNav;
    private String downHideNews;
    private int syRetainOldNew;
    private List<ItemBean> item;
    private int showAdvert;
    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTopsize() {
        return topsize;
    }

    public void setTopsize(int topsize) {
        this.topsize = topsize;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public int getSyRetainOldNew() {
        return syRetainOldNew;
    }

    public void setSyRetainOldNew(int syRetainOldNew) {
        this.syRetainOldNew = syRetainOldNew;
    }

    public String getDownHideNews() {
        return downHideNews;
    }

    public void setDownHideNews(String downHideNews) {
        this.downHideNews = downHideNews;
    }

    public String getDownHideFocusNav() {
        return downHideFocusNav;
    }

    public void setDownHideFocusNav(String downHideFocusNav) {
        this.downHideFocusNav = downHideFocusNav;
    }

    public int getShowAdvert() {
        return showAdvert;
    }

    public void setShowAdvert(int showAdvert) {
        this.showAdvert = showAdvert;
    }

    public static class ItemBean implements Serializable, MultiItemEntity {
        //广告类型
        public static final int TYPE_ADVERT_TITLEIMG = 1;

        public static final int TYPE_ADVERT_SLIDEIMG = 2;

        public static final int TYPE_ADVERT_LONGIMG = 3;
        //图片类型
        public static final int TYPE_SLIDE = 4;
        //视频类型
        public static final int TYPE_PHVIDEO = 5;

        //显示形式单图
        public static final int TYPE_DOC_TITLEIMG = 6;
        //显示形式多图
        public static final int TYPE_DOC_SLIDEIMG = 7;


        @Override
        public String toString() {
            return "ItemBean{" +
                    "type='" + type + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", title='" + title + '\'' +
                    ", showType='" + showType + '\'' +
                    ", source='" + source + '\'' +
                    ", subscribe=" + subscribe +
                    ", updateTime='" + updateTime + '\'' +
                    ", id='" + id + '\'' +
                    ", documentId='" + documentId + '\'' +
                    ", staticId='" + staticId + '\'' +
                    ", style=" + style +
                    ", commentsUrl='" + commentsUrl + '\'' +
                    ", comments='" + comments + '\'' +
                    ", commentsall='" + commentsall + '\'' +
                    ", link=" + link +
                    ", simId='" + simId + '\'' +
                    ", reftype='" + reftype + '\'' +
                    ", recomToken='" + recomToken + '\'' +
                    '}';
        }

        /**
         * type : doc
         * thumbnail : http://d.ifengimg.com/w198_h141_q100/p2.ifengimg.com/cmpp/2017/07/03/a9e21d53672c5b16db9c19359e498fb3_size44_w168_h120.jpg
         * online : 1
         * title : 民政部：强降雨致南方8省48人死亡失踪 近千万人受灾
         * showType : 0
         * source : 央视网
         * subscribe : {"cateid":"央视网","type":"source","catename":"央视网","description":""}
         * updateTime : 2017/07/03 17:12:53
         * id : http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470051364453&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW_DOWN
         * documentId : cmpp_034470051364453
         * staticId : cmpp_034470051364453
         * style : {"backreason":["来源:央视网","内容质量差","旧闻、重复","标题党"],"view":"titleimg"}
         * commentsUrl : http://news.ifeng.com/a/20170703/51364453_0.shtml
         * comments : 30
         * commentsall : 154
         * link : {"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470051364453&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW_DOWN","weburl":"http://share.iclient.ifeng.com/sharenews.f?aid=034470051364453"}
         * simId : clusterId_13026763
         * reftype : editor
         * recomToken : 788bebb2-3423-4301-a4d5-df1818b7012b
         * hasSlide : true
         */

        @Override
        public int getItemType() {
            return itemType;
        }

        public int itemType;
        private String type;
        private String thumbnail;
        private String title;
        private String showType;
        private String source;
        private SubscribeBean subscribe;
        private String updateTime;
        private String id;
        private String documentId;
        private String staticId;
        private StyleBean style;
        private String commentsUrl;
        private String comments;
        private String commentsall;
        private LinkBean link;
        private String simId;
        private String reftype;
        private String recomToken;
        private String payload;
        private String intro;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public SubscribeBean getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(SubscribeBean subscribe) {
            this.subscribe = subscribe;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getStaticId() {
            return staticId;
        }

        public void setStaticId(String staticId) {
            this.staticId = staticId;
        }

        public StyleBean getStyle() {
            return style;
        }

        public void setStyle(StyleBean style) {
            this.style = style;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getCommentsall() {
            return commentsall;
        }

        public void setCommentsall(String commentsall) {
            this.commentsall = commentsall;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }

        public String getSimId() {
            return simId;
        }

        public void setSimId(String simId) {
            this.simId = simId;
        }

        public String getReftype() {
            return reftype;
        }

        public void setReftype(String reftype) {
            this.reftype = reftype;
        }

        public String getRecomToken() {
            return recomToken;
        }

        public void setRecomToken(String recomToken) {
            this.recomToken = recomToken;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public static class SubscribeBean {
            @Override
            public String toString() {
                return "SubscribeBean{" +
                        "cateid='" + cateid + '\'' +
                        ", type='" + type + '\'' +
                        ", catename='" + catename + '\'' +
                        '}';
            }

            /**
             * cateid : 央视网
             * type : source
             * catename : 央视网
             * description :
             */


            private String cateid;
            private String type;
            private String catename;

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

        }

        public static class StyleBean {
            @Override
            public String toString() {
                return "StyleBean{" +
                        "view='" + view + '\'' +
                        ", backreason=" + backreason +
                        ", images=" + images +
                        '}';
            }

            /**
             * backreason : ["来源:央视网","内容质量差","旧闻、重复","标题党"]
             * view : titleimg
             */


            private String type ;
            private String attribute;
            private String view;
            private List<String> backreason;
            private List<String> images;

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }

            public List<String> getBackreason() {
                return backreason;
            }

            public void setBackreason(List<String> backreason) {
                this.backreason = backreason;
            }

            public String getAttribute() {
                return attribute;
            }

            public void setAttribute(String attribute) {
                this.attribute = attribute;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class LinkBean {

            private String type;
            private String url;
            private String weburl;

            @Override
            public String toString() {
                return "LinkBean{" +
                        "type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        ", weburl='" + weburl + '\'' +
                        '}';
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWeburl() {
                return weburl;
            }

            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
        }
    }
}
