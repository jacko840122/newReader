package com.common.http.data;

import java.util.List;

public class Categorymenu  {

    /**
     * success : true
     * message : 成功
     * status : 200
     * data : [{"id":"39","typename":"文学经典","up_typeid":"36","b_sort":"1","status":"1"},{"id":"72","typename":"阿斯顿","up_typeid":"71","b_sort":"1","status":"1"},{"id":"70","typename":"阿萨法说的","up_typeid":"69","b_sort":"1","status":"1"},{"id":"74","typename":"阿德法所得","up_typeid":"73","b_sort":"1","status":"1"},{"id":"76","typename":"阿道夫","up_typeid":"75","b_sort":"1","status":"1"},{"id":"78","typename":"艾丝凡","up_typeid":"77","b_sort":"1","status":"1"},{"id":"84","typename":"名人传记","up_typeid":"36","b_sort":"4","status":"1"},{"id":"111","typename":"科学技术","up_typeid":"36","b_sort":"5","status":"1"},{"id":"112","typename":"人生修养","up_typeid":"36","b_sort":"6","status":"1"}]
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
         * id : 39
         * typename : 文学经典
         * up_typeid : 36
         * b_sort : 1
         * status : 1
         */

        private String id;
        private String typename;
        private String up_typeid;
        private String b_sort;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getUp_typeid() {
            return up_typeid;
        }

        public void setUp_typeid(String up_typeid) {
            this.up_typeid = up_typeid;
        }

        public String getB_sort() {
            return b_sort;
        }

        public void setB_sort(String b_sort) {
            this.b_sort = b_sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
