package com.jun.znews.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;


public class NewsOtherVideo implements Serializable {
    private List<GuidRelativeVideoInfoBean> guidRelativeVideoInfo;

    public List<GuidRelativeVideoInfoBean> getGuidRelativeVideoInfo() {
        return guidRelativeVideoInfo;
    }

    public void setGuidRelativeVideoInfo(List<GuidRelativeVideoInfoBean> guidRelativeVideoInfo) {
        this.guidRelativeVideoInfo = guidRelativeVideoInfo;
    }


    public static class GuidRelativeVideoInfoBean implements MultiItemEntity {
        /**
         * guid : 728a34ce-3b4e-49da-8aa9-ef214300e217
         * name : 空姐遇害案告破！DNA鉴定打捞尸体确系嫌犯刘某华
         * praise : 9
         * tread : 1
         * playTime : 10587
         * type : phvideo
         * createDate : 2018-05-12 12:06:04
         * duration : 61
         * columnName : 火龙果传媒1
         * columnId : 533755
         * seTitle :
         * cpName : 一点资讯
         * searchPath : 55-56
         * shareUrl : http://share.iclient.ifeng.com/sharenews.f?guid=728a34ce-3b4e-49da-8aa9-ef214300e217
         * newShareUrl :
         * commentNo : 27
         * commentsAll : 27
         * weMedia : {"headPic":"http://si1.go2yd.com/get-image/071eW8v3PYe","name":"火龙果传媒1","desc":"火龙果传媒，为您提供最新最热的社会焦点、奇闻趣事、古今轶事，丰富您的视野，快乐您的生活！","id":"533755","type":"normal"}
         * files : [{"useType":102,"filesize":4907,"mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2018/05/12/27411751-102-009-171214.mp4","spliteTime":""},{"useType":103,"filesize":8233,"mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2018/05/12/27411751-544-080-171214.mp4","spliteTime":""},{"useType":274,"filesize":478,"mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2018/05/12/27411751-535-066-171214.mp3","spliteTime":""},{"useType":130,"filesize":0,"mediaUrl":"http://d.ifengimg.com/w480_h360_q80/p0.ifengimg.com/pmop/2018/0512/EB998C903596554ED0D47BE36F579379A87CD9BC_size67_w1080_h720.jpeg","spliteTime":""},{"useType":140,"filesize":0,"mediaUrl":"http://d.ifengimg.com/w120_h90_q80/p0.ifengimg.com/pmop/2018/0512/EB998C903596554ED0D47BE36F579379A87CD9BC_size67_w1080_h720.jpeg","spliteTime":""},{"useType":150,"filesize":0,"mediaUrl":"http://d.ifengimg.com/w200_h150_q80/p0.ifengimg.com/pmop/2018/0512/EB998C903596554ED0D47BE36F579379A87CD9BC_size67_w1080_h720.jpeg","spliteTime":""}]
         * itemId : 14771012
         */

        int MType = 0 ;


        private String guid;
        private String name;
        private String praise;
        private String tread;
        private String playTime;
        private String type;
        private String createDate;
        private int duration;
        private String columnName;
        private String columnId;
        private String seTitle;
        private String cpName;
        private String searchPath;
        private String shareUrl;
        private String newShareUrl;
        private String commentNo;
        private String commentsAll;
        private WeMediaBean weMedia;
        private String itemId;
        private List<FilesBean> files;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnId() {
            return columnId;
        }

        public void setColumnId(String columnId) {
            this.columnId = columnId;
        }

        public String getSeTitle() {
            return seTitle;
        }

        public void setSeTitle(String seTitle) {
            this.seTitle = seTitle;
        }

        public String getCpName() {
            return cpName;
        }

        public void setCpName(String cpName) {
            this.cpName = cpName;
        }

        public String getSearchPath() {
            return searchPath;
        }

        public void setSearchPath(String searchPath) {
            this.searchPath = searchPath;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getNewShareUrl() {
            return newShareUrl;
        }

        public void setNewShareUrl(String newShareUrl) {
            this.newShareUrl = newShareUrl;
        }

        public String getCommentNo() {
            return commentNo;
        }

        public void setCommentNo(String commentNo) {
            this.commentNo = commentNo;
        }

        public String getCommentsAll() {
            return commentsAll;
        }

        public void setCommentsAll(String commentsAll) {
            this.commentsAll = commentsAll;
        }

        public WeMediaBean getWeMedia() {
            return weMedia;
        }

        public void setWeMedia(WeMediaBean weMedia) {
            this.weMedia = weMedia;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public List<FilesBean> getFiles() {
            return files;
        }

        public void setFiles(List<FilesBean> files) {
            this.files = files;
        }

        @Override
        public int getItemType() {
            return MType;
        }

        public static class WeMediaBean {
            /**
             * headPic : http://si1.go2yd.com/get-image/071eW8v3PYe
             * name : 火龙果传媒1
             * desc : 火龙果传媒，为您提供最新最热的社会焦点、奇闻趣事、古今轶事，丰富您的视野，快乐您的生活！
             * id : 533755
             * type : normal
             */

            private String headPic;
            private String name;
            private String desc;
            private String id;
            private String type;

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class FilesBean {
            /**
             * useType : 102
             * filesize : 4907
             * mediaUrl : http://ips.ifeng.com/video19.ifeng.com/video09/2018/05/12/27411751-102-009-171214.mp4
             * spliteTime :
             */

            private int useType;
            private int filesize;
            private String mediaUrl;
            private String spliteTime;

            public int getUseType() {
                return useType;
            }

            public void setUseType(int useType) {
                this.useType = useType;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public String getMediaUrl() {
                return mediaUrl;
            }

            public void setMediaUrl(String mediaUrl) {
                this.mediaUrl = mediaUrl;
            }

            public String getSpliteTime() {
                return spliteTime;
            }

            public void setSpliteTime(String spliteTime) {
                this.spliteTime = spliteTime;
            }
        }
    }
}

