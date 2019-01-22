package com.common.http.data;

import java.util.List;

public class Pzlist  {
    /**
     * success : true
     * message : 成功
     * status : 200
     * data : [{"id":"7","bid":"92","type":"1","chapter":"1","text":"宴桃园豪杰三结义　斩黄巾英雄首立功,第001回　宴桃园豪杰三结义　斩黄巾英雄首立功    滚滚长江东逝水，浪花淘尽英雄。是非成败转头空。    青山依旧在，几度夕阳红。白发渔樵江渚上，惯    看秋月春风。一壶浊酒喜相逢。古今多少事，都付    笑谈中\u2014\u2014    调寄《临江仙》    话说天下大势，分久必合，合久必分。","pizhu":"测试批注"},{"id":"8","bid":"92","type":"1","chapter":"118","text":"会依言，即遣人赍表进赴洛阳，言邓艾专权恣肆，结好蜀人，早晚必反矣。于是朝中文武皆惊。会又今人于中途截了邓艾表文，按艾笔法，改写傲慢之辞，以实己之语。","pizhu":"测试"},{"id":"12","bid":"92","type":"1","chapter":"118","text":"却说邓艾封师纂为益州刺史，牵弘、王颀等各领州郡；又于绵竹筑台以彰战功，大会蜀中诸官饮宴。艾酒至半酣，乃指众官曰：\u201c汝等幸遇我，故有今日耳。若遇他将，必皆殄灭矣。\u201d多官起身拜谢。忽蒋显至，说姜维自降钟镇西了。艾因此痛恨钟会。遂修书令人赍赴洛阳，致晋公司马昭。昭得书视之。书曰：\u201c臣艾切谓兵有先声而后实者，今因平蜀之势以乘吴，此席卷之时也。然大举之后，将士疲劳，不可便用；宜留陇右兵二万、蜀兵二万，煮盐兴冶，并造舟船，预备顺流之计；然后发使，告以利害，吴可不征而定也。今宜厚待刘禅，以致孙休；若便送禅来京，吴人必疑，则于向化之心不劝。且权留之于蜀，须来年冬月抵京。今即可封禅为扶风王，锡以资财，供其左右，爵其子为公侯，以显归命之宠：则吴人畏威怀德，望风而从矣。\u201d","pizhu":"阿瑟东"}]
     */

    private boolean success;
    private String message;
    private String status;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 7
         * bid : 92
         * type : 1
         * chapter : 1
         * text : 宴桃园豪杰三结义　斩黄巾英雄首立功,第001回　宴桃园豪杰三结义　斩黄巾英雄首立功    滚滚长江东逝水，浪花淘尽英雄。是非成败转头空。    青山依旧在，几度夕阳红。白发渔樵江渚上，惯    看秋月春风。一壶浊酒喜相逢。古今多少事，都付    笑谈中——    调寄《临江仙》    话说天下大势，分久必合，合久必分。
         * pizhu : 测试批注
         */

        private String id;
        private String bid;
        private String type;
        private String chapter;
        private String text;
        private String pizhu;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPizhu() {
            return pizhu;
        }

        public void setPizhu(String pizhu) {
            this.pizhu = pizhu;
        }
    }
}
