package com.common.http.data;

import java.io.Serializable;
import java.util.List;

public class Feellist {

    /**
     * success : true
     * message : 成功
     * status : 200
     * data : [{"id":"6","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082052.jpg","time":"1548317720"},{"id":"7","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082052.jpg","time":"1548317728"},{"id":"8","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082052.jpg","time":"1548317733"},{"id":"9","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082058.jpg","time":"1548318058"},{"id":"10","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082105.jpg","time":"1548318065"},{"id":"11","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082331.jpg","time":"1548318211"},{"id":"12","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082441.jpg","time":"1548318281"},{"id":"13","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082453.jpg","time":"1548318293"},{"id":"14","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124082814.jpg","time":"1548318494"},{"id":"15","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124085452.jpg","time":"1548320092"},{"id":"16","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124093118.jpg","time":"1548322278"},{"id":"17","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124093134.jpg","time":"1548322294"},{"id":"18","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124101039.jpg","time":"1548324639"},{"id":"19","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124101050.jpg","time":"1548324650"},{"id":"20","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124101215.jpg","time":"1548324735"},{"id":"21","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124112049.jpg","time":"1548328849"},{"id":"22","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124115435.jpg","time":"1548330875"},{"id":"23","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124115840.jpg","time":"1548331120"},{"id":"24","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124120229.jpg","time":"1548331349"},{"id":"25","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124122749.jpg","time":"1548332869"},{"id":"26","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124123300.jpg","time":"1548333180"},{"id":"27","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124123541.jpg","time":"1548333341"},{"id":"28","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124125731.jpg","time":"1548334651"},{"id":"29","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124125855.jpg","time":"1548334735"},{"id":"30","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124010815.jpg","time":"1548335295"},{"id":"31","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124011951.jpg","time":"1548335991"},{"id":"32","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124012308.jpg","time":"1548336188"},{"id":"33","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124032302.jpg","time":"1548343382"},{"id":"34","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124032331.jpg","time":"1548343411"},{"id":"35","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124032417.jpg","time":"1548343457"},{"id":"36","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124061033.png","time":"1548353433"},{"id":"37","bid":"68","uid":"7","chapter":"1","fnum":null,"path":"http://47.105.162.222/content/feel/20190124/","name":"190124061052.png","time":"1548353452"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 6
         * bid : 68
         * uid : 7
         * chapter : 1
         * fnum : null
         * path : http://47.105.162.222/content/feel/20190124/
         * name : 190124082052.jpg
         * time : 1548317720
         */

        private String id;
        private String bid;
        private String uid;
        private String chapter;
        private Object fnum;
        private String path;
        private String name;
        private String time;

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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public Object getFnum() {
            return fnum;
        }

        public void setFnum(Object fnum) {
            this.fnum = fnum;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
