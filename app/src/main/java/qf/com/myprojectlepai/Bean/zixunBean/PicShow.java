package qf.com.myprojectlepai.Bean.zixunBean;

/**
 * Created by Administrator on 16-9-8.
 */
public class PicShow {

    /**
     * pic_datil : 摄影：飞虎
     模特：mini

     * gq : http://img3.fengniao.com/forum/attachpics/932/200/37279990_1000.jpg
     * sl :
     * web_url : http://bbs.fengniao.com/forum/pic/slide_101_9284016_82372002.html
     */

    private PicBean pic;

    public PicBean getPic() {
        return pic;
    }

    public void setPic(PicBean pic) {
        this.pic = pic;
    }

    public static class PicBean {
        private String pic_datil;
        private String gq;

        public String getPic_datil() {
            return pic_datil;
        }

        public void setPic_datil(String pic_datil) {
            this.pic_datil = pic_datil;
        }

        public String getGq() {
            return gq;
        }

        public void setGq(String gq) {
            this.gq = gq;
        }
    }
}
