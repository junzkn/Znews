package com.jun.znews.bean;

import java.io.Serializable;
import java.util.List;

public class NewsVideoBean implements Serializable {

    private List<VideoBean> singleVideoInfo;

    public List<VideoBean> getSingleVideoInfo() {
        return singleVideoInfo;
    }

    public void setSingleVideoInfo(List<VideoBean> singleVideoInfo) {
        this.singleVideoInfo = singleVideoInfo;
    }

    public static class VideoBean implements Serializable{
        /**
         * videoURLLow : http://ips.ifeng.com/video19.ifeng.com/video09/2017/12/13/15992419-102-9987625-065712.mp4?ifsign=1
         * videoURLMid : http://ips.ifeng.com/video19.ifeng.com/video09/2017/12/13/15992419-102-9987625-065712.mp4?ifsign=1
         * videoURLHigh : http://ips.ifeng.com/video19.ifeng.com/video09/2017/12/13/15992419-102-9987625-065712.mp4?ifsign=1
         * GUID : 732740c1-c175-4a28-80fe-2428e8dc7174
         * praise : 34612
         * tread : 24
         * playTime : 190361
         * imgURL : http://p0.ifengimg.com/web/2018_03/9f449a7806f0e19_w1154_h720.jpg
         * smallImgURL : http://d.ifengimg.com/w120_h90/p0.ifengimg.com/web/2018_03/9f449a7806f0e19_w1154_h720.jpg
         * largeImgURL : http://d.ifengimg.com/w480_h360/p0.ifengimg.com/web/2018_03/9f449a7806f0e19_w1154_h720.jpg
         * thumbnailVertical : http://p3.ifengimg.com/web/2017_50/29011c1c3681f33_w1154_h720.jpg
         * videoPublishTime : 2017-12-13 06:55:35
         * shareURL : http://m.ifeng.com/sharenews.f?guid=732740c1-c175-4a28-80fe-2428e8dc7174&forward=1
         * commentsUrl : 732740c1-c175-4a28-80fe-2428e8dc7174
         * type : phvideo
         * id : 10104318
         * statisticID :
         * title : 宝宝以为妈妈睡着了……接下来的动作把人笑翻，萌死了！
         * filesize : 7725
         * videoLength : 00:01:55
         * longTitle : 宝宝以为妈妈睡着了……接下来的动作把人笑翻，萌死了！
         * columnName : 天天看视
         * CP : 今日头条
         * status : 1
         * columnId : 833718
         * weMedia : {"desc":"中华龙凤","id":"833718","name":"天天看视","type":"normal","headPic":"http://p0.ifengimg.com/pmop/20171120/9afdaf0b-0cf1-4291-b57b-3853015e4b5d.png"}
         * newStatus : 1
         * category : 搞笑
         */

        private String videoURLLow;
        private String videoURLMid;
        private String videoURLHigh;
        private String GUID;
        private String praise;
        private String tread;
        private String playTime;
        private String imgURL;
        private String smallImgURL;
        private String largeImgURL;
        private String thumbnailVertical;
        private String videoPublishTime;
        private String shareURL;
        private String commentsUrl;
        private String type;
        private int id;
        private String statisticID;
        private String title;
        private String filesize;
        private String videoLength;
        private String longTitle;
        private String columnName;
        private String CP;
        private int status;
        private String columnId;
        private WeMediaBean weMedia;
        private String newStatus;
        private String category;

        public String getVideoURLLow() {
            return videoURLLow;
        }

        public void setVideoURLLow(String videoURLLow) {
            this.videoURLLow = videoURLLow;
        }

        public String getVideoURLMid() {
            return videoURLMid;
        }

        public void setVideoURLMid(String videoURLMid) {
            this.videoURLMid = videoURLMid;
        }

        public String getVideoURLHigh() {
            return videoURLHigh;
        }

        public void setVideoURLHigh(String videoURLHigh) {
            this.videoURLHigh = videoURLHigh;
        }

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getTread() {
            return tread;
        }

        public void setTread(String tread) {
            this.tread = tread;
        }

        public String getPlayTime() {
            return playTime;
        }

        public void setPlayTime(String playTime) {
            this.playTime = playTime;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getSmallImgURL() {
            return smallImgURL;
        }

        public void setSmallImgURL(String smallImgURL) {
            this.smallImgURL = smallImgURL;
        }

        public String getLargeImgURL() {
            return largeImgURL;
        }

        public void setLargeImgURL(String largeImgURL) {
            this.largeImgURL = largeImgURL;
        }

        public String getThumbnailVertical() {
            return thumbnailVertical;
        }

        public void setThumbnailVertical(String thumbnailVertical) {
            this.thumbnailVertical = thumbnailVertical;
        }

        public String getVideoPublishTime() {
            return videoPublishTime;
        }

        public void setVideoPublishTime(String videoPublishTime) {
            this.videoPublishTime = videoPublishTime;
        }

        public String getShareURL() {
            return shareURL;
        }

        public void setShareURL(String shareURL) {
            this.shareURL = shareURL;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatisticID() {
            return statisticID;
        }

        public void setStatisticID(String statisticID) {
            this.statisticID = statisticID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFilesize() {
            return filesize;
        }

        public void setFilesize(String filesize) {
            this.filesize = filesize;
        }

        public String getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(String videoLength) {
            this.videoLength = videoLength;
        }

        public String getLongTitle() {
            return longTitle;
        }

        public void setLongTitle(String longTitle) {
            this.longTitle = longTitle;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getCP() {
            return CP;
        }

        public void setCP(String CP) {
            this.CP = CP;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getColumnId() {
            return columnId;
        }

        public void setColumnId(String columnId) {
            this.columnId = columnId;
        }

        public WeMediaBean getWeMedia() {
            return weMedia;
        }

        public void setWeMedia(WeMediaBean weMedia) {
            this.weMedia = weMedia;
        }

        public String getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(String newStatus) {
            this.newStatus = newStatus;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public static class WeMediaBean {
            /**
             * desc : 中华龙凤
             * id : 833718
             * name : 天天看视
             * type : normal
             * headPic : http://p0.ifengimg.com/pmop/20171120/9afdaf0b-0cf1-4291-b57b-3853015e4b5d.png
             */

            private String desc;
            private String id;
            private String name;
            private String type;
            private String headPic;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }
        }
    }
}

