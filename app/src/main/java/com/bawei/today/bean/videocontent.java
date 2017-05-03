package com.bawei.today.bean;

import java.util.List;

/**
 * Created by admin on 2017/3/18.
 */

public class videocontent {

        /**
         * topicImg : http://vimg2.ws.126.net/image/snapshot/2017/2/5/A/VCDEOBE5A.jpg
         * videosource : 新媒体
         * mp4Hd_url : null
         * topicDesc : 分享最经典的港台电影、精彩片段，陪您一起感受电影世界的精彩！这里是经典电影，如果您喜欢我请关注我哟！
         * topicSid : VCDEOBE4V
         * cover : http://vimg3.ws.126.net/image/snapshot/2017/3/1/4/VCESQQF14.jpg
         * title : 钱小豪为纪念林正英而拍的最后一部僵尸片，堪称国际大片
         * playCount : 0
         * replyBoard : video_bbs
         * videoTopic : {"alias":"分享最经典的港台电影精彩片段","tname":"经典电影","ename":"T1488195205753","tid":"T1488195205753","topic_icons":"http://dingyue.nosdn.127.net/Jj0JcqgP3cXXqq0DQL07TjC01Sb1=n33YrdoY3x26n8o61488195205427.jpg"}
         * sectiontitle :
         * replyid : CESOM5UV008535RB
         * description :
         * mp4_url : http://flv2.bn.netease.com/videolib3/1703/18/OxmgE4460/SD/OxmgE4460-mobile.mp4
         * length : 111
         * playersize : 1
         * m3u8Hd_url : null
         * vid : VCESOM5UV
         * m3u8_url : http://flv2.bn.netease.com/videolib3/1703/18/OxmgE4460/SD/movie_index.m3u8
         * ptime : 2017-03-18 13:24:22
         * topicName : 经典电影
         */

        private String topicImg;
        private String videosource;
        private Object mp4Hd_url;
        private String topicDesc;
        private String topicSid;
        private String cover;
        private String title;
        private int playCount;
        private String replyBoard;
        private VideoTopicBean videoTopic;
        private String sectiontitle;
        private String replyid;
        private String description;
        private String mp4_url;
        private int length;
        private int playersize;
        private Object m3u8Hd_url;
        private String vid;
        private String m3u8_url;
        private String ptime;
        private String topicName;

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getVideosource() {
            return videosource;
        }

        public void setVideosource(String videosource) {
            this.videosource = videosource;
        }

        public Object getMp4Hd_url() {
            return mp4Hd_url;
        }

        public void setMp4Hd_url(Object mp4Hd_url) {
            this.mp4Hd_url = mp4Hd_url;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

        public String getTopicSid() {
            return topicSid;
        }

        public void setTopicSid(String topicSid) {
            this.topicSid = topicSid;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getReplyBoard() {
            return replyBoard;
        }

        public void setReplyBoard(String replyBoard) {
            this.replyBoard = replyBoard;
        }

        public VideoTopicBean getVideoTopic() {
            return videoTopic;
        }

        public void setVideoTopic(VideoTopicBean videoTopic) {
            this.videoTopic = videoTopic;
        }

        public String getSectiontitle() {
            return sectiontitle;
        }

        public void setSectiontitle(String sectiontitle) {
            this.sectiontitle = sectiontitle;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getPlayersize() {
            return playersize;
        }

        public void setPlayersize(int playersize) {
            this.playersize = playersize;
        }

        public Object getM3u8Hd_url() {
            return m3u8Hd_url;
        }

        public void setM3u8Hd_url(Object m3u8Hd_url) {
            this.m3u8Hd_url = m3u8Hd_url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getM3u8_url() {
            return m3u8_url;
        }

        public void setM3u8_url(String m3u8_url) {
            this.m3u8_url = m3u8_url;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public static class VideoTopicBean {
            /**
             * alias : 分享最经典的港台电影精彩片段
             * tname : 经典电影
             * ename : T1488195205753
             * tid : T1488195205753
             * topic_icons : http://dingyue.nosdn.127.net/Jj0JcqgP3cXXqq0DQL07TjC01Sb1=n33YrdoY3x26n8o61488195205427.jpg
             */

            private String alias;
            private String tname;
            private String ename;
            private String tid;
            private String topic_icons;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTopic_icons() {
                return topic_icons;
            }

            public void setTopic_icons(String topic_icons) {
                this.topic_icons = topic_icons;
            }
        }
    }

