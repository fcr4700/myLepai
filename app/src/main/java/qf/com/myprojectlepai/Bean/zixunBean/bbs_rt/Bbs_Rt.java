package qf.com.myprojectlepai.Bean.zixunBean.bbs_rt;

import java.util.List;

/**
 * Created by Administrator on 16-9-8.
 */
public class Bbs_Rt {

    /**
     * title : 风雪219-2016梧桐凤凰新藏线骑行纪实
     * author : oysw
     * views : 26633
     * reply : 1135
     * doc_url : http://api.fengniao.com/app_ipad/bbs_hot_detail.php?tid=9268127&fid=15&isPad=1&cid=0&sid=999&page=1
     * doc_url_v2 : http://api.fengniao.com/app_ipad/bbs_hot_detail_v2.php?tid=9268127&fid=15&isPad=1&cid=0&sid=999&page=1
     * docTotalPage : 57
     * tid : 9268127
     * fid : 15
     * web_url : http://bbs.fengniao.com/forum/9268127.html
     * docLzTotalPage : 53
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String title;
        private String author;
        private String views;
        private String reply;
        private String fid;
        private String web_url;
        private int docLzTotalPage;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public int getDocLzTotalPage() {
            return docLzTotalPage;
        }

        public void setDocLzTotalPage(int docLzTotalPage) {
            this.docLzTotalPage = docLzTotalPage;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", views='" + views + '\'' +
                    ", reply='" + reply + '\'' +
                    ", fid='" + fid + '\'' +
                    ", web_url='" + web_url + '\'' +
                    ", docLzTotalPage=" + docLzTotalPage +
                    '}';
        }
    }
}
