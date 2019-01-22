package com.common.http.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bookslist  implements Serializable {


    /**
     * success : true
     * message : 成功
     * status : 200
     * data : {"0":{"id":"92","sid":"143","b_name":"三国演义","b_author":"罗贯中","b_press":"","publish_time":"0","b_cover":"0","s_key":"三国","b_lead":"","b_sourceid":"1","addtime":"1546506547","contract_addtime":"0","contract_endtime":"0","b_introduction":"《三国演义》，中国古典四大名著之一。\n公元三世纪东汉末年，以曹操、刘备、孙权为首的魏蜀吴三个政治、军事集团之间的矛盾和斗争。由于各国后主过于无能而亡国。\n蜀后主刘禅被钟、邓合军擒于成都，吴后主惧战而降，魏后主曹奂被迫移位于司马炎改年号为晋，从此天下皆归司马掌控。正所谓:\u201c天下大势，合久必分，分久必合。\u201d\n该小说一方面反映了真实的三国历史，照顾到读者希望了解真实历史的需要；另一方面，根据明朝社会的实际情况对三国人物进行了夸张、美化、丑化等等。","cahepath":null,"status":"3","edittime":"1547110880","dowsnum":"0","spublish_range":null,"readnum":"0","typename":"魔法阅读"},"1":{"id":"88","sid":"138","b_name":"三十六计","b_author":"佚名","b_press":"","publish_time":"0","b_cover":null,"s_key":"三十六计","b_lead":"","b_sourceid":"1","addtime":"1546489111","contract_addtime":"0","contract_endtime":"0","b_introduction":"《三十六计》，中国古代三十六个兵法策略，或称三十六策，是指中国古代三十六个兵法策略，语源于南北朝，成书于明清。它是根据中国古代军事思想和丰富的斗争经验总结而成的兵书，是中华民族悠久非物质文化遗产之一。","cahepath":null,"status":"3","edittime":"1547113213","dowsnum":"0","spublish_range":null,"readnum":"0","typename":"人生修养"},"2":{"id":"64","sid":"112","b_name":"唐诗三百首","b_author":"成涛","b_press":"","publish_time":"0","b_cover":"/20190103/tangshisanbaishou/META_INF/cover.jpg","s_key":"唐诗","b_lead":"","b_sourceid":"1","addtime":"1546487454","contract_addtime":"0","contract_endtime":"0","b_introduction":"《唐诗三百首》是一部流传很广的唐诗选集。唐朝（618年~907年）二百八十九年间，是中国诗歌发展的黄金时代，云蒸霞蔚，名家辈出，唐诗数量多达五万余首。\n本书共选入唐代诗人77位，计311首诗，其中五言古诗33首，乐府46首，七言古诗28首，七言律诗50首，五言绝句29首，七言绝句51首，诸诗配有注释和评点。 五言古诗简称五古有，是唐代诗坛较为流行的体裁。唐人五古笔力豪纵，气象万千，直接用于叙事、抒情、议论、写景，使其功能得到了空前的发挥，其代表作家李白、杜甫、王维、孟浩然、韦应物等。","cahepath":null,"status":"3","edittime":"1547110094","dowsnum":"0","spublish_range":null,"readnum":"0","typename":"魔法阅读"},"total":"3"}
     */

    private boolean success;
    private String message;
    private String status;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * 0 : {"id":"92","sid":"143","b_name":"三国演义","b_author":"罗贯中","b_press":"","publish_time":"0","b_cover":"0","s_key":"三国","b_lead":"","b_sourceid":"1","addtime":"1546506547","contract_addtime":"0","contract_endtime":"0","b_introduction":"《三国演义》，中国古典四大名著之一。\n公元三世纪东汉末年，以曹操、刘备、孙权为首的魏蜀吴三个政治、军事集团之间的矛盾和斗争。由于各国后主过于无能而亡国。\n蜀后主刘禅被钟、邓合军擒于成都，吴后主惧战而降，魏后主曹奂被迫移位于司马炎改年号为晋，从此天下皆归司马掌控。正所谓:\u201c天下大势，合久必分，分久必合。\u201d\n该小说一方面反映了真实的三国历史，照顾到读者希望了解真实历史的需要；另一方面，根据明朝社会的实际情况对三国人物进行了夸张、美化、丑化等等。","cahepath":null,"status":"3","edittime":"1547110880","dowsnum":"0","spublish_range":null,"readnum":"0","typename":"魔法阅读"}
         * 1 : {"id":"88","sid":"138","b_name":"三十六计","b_author":"佚名","b_press":"","publish_time":"0","b_cover":null,"s_key":"三十六计","b_lead":"","b_sourceid":"1","addtime":"1546489111","contract_addtime":"0","contract_endtime":"0","b_introduction":"《三十六计》，中国古代三十六个兵法策略，或称三十六策，是指中国古代三十六个兵法策略，语源于南北朝，成书于明清。它是根据中国古代军事思想和丰富的斗争经验总结而成的兵书，是中华民族悠久非物质文化遗产之一。","cahepath":null,"status":"3","edittime":"1547113213","dowsnum":"0","spublish_range":null,"readnum":"0","typename":"人生修养"}
         * 2 : {"id":"64","sid":"112","b_name":"唐诗三百首","b_author":"成涛","b_press":"","publish_time":"0","b_cover":"/20190103/tangshisanbaishou/META_INF/cover.jpg","s_key":"唐诗","b_lead":"","b_sourceid":"1","addtime":"1546487454","contract_addtime":"0","contract_endtime":"0","b_introduction":"《唐诗三百首》是一部流传很广的唐诗选集。唐朝（618年~907年）二百八十九年间，是中国诗歌发展的黄金时代，云蒸霞蔚，名家辈出，唐诗数量多达五万余首。\n本书共选入唐代诗人77位，计311首诗，其中五言古诗33首，乐府46首，七言古诗28首，七言律诗50首，五言绝句29首，七言绝句51首，诸诗配有注释和评点。 五言古诗简称五古有，是唐代诗坛较为流行的体裁。唐人五古笔力豪纵，气象万千，直接用于叙事、抒情、议论、写景，使其功能得到了空前的发挥，其代表作家李白、杜甫、王维、孟浩然、韦应物等。","cahepath":null,"status":"3","edittime":"1547110094","dowsnum":"0","spublish_range":null,"readnum":"0","typename":"魔法阅读"}
         * total : 3
         */

        @SerializedName("0")
        private _$0Bean _$0;
        @SerializedName("1")
        private _$1Bean _$1;
        @SerializedName("2")
        private _$2Bean _$2;
        private String total;

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public _$1Bean get_$1() {
            return _$1;
        }

        public void set_$1(_$1Bean _$1) {
            this._$1 = _$1;
        }

        public _$2Bean get_$2() {
            return _$2;
        }

        public void set_$2(_$2Bean _$2) {
            this._$2 = _$2;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public static class _$0Bean {
            /**
             * id : 92
             * sid : 143
             * b_name : 三国演义
             * b_author : 罗贯中
             * b_press :
             * publish_time : 0
             * b_cover : 0
             * s_key : 三国
             * b_lead :
             * b_sourceid : 1
             * addtime : 1546506547
             * contract_addtime : 0
             * contract_endtime : 0
             * b_introduction : 《三国演义》，中国古典四大名著之一。
             公元三世纪东汉末年，以曹操、刘备、孙权为首的魏蜀吴三个政治、军事集团之间的矛盾和斗争。由于各国后主过于无能而亡国。
             蜀后主刘禅被钟、邓合军擒于成都，吴后主惧战而降，魏后主曹奂被迫移位于司马炎改年号为晋，从此天下皆归司马掌控。正所谓:“天下大势，合久必分，分久必合。”
             该小说一方面反映了真实的三国历史，照顾到读者希望了解真实历史的需要；另一方面，根据明朝社会的实际情况对三国人物进行了夸张、美化、丑化等等。
             * cahepath : null
             * status : 3
             * edittime : 1547110880
             * dowsnum : 0
             * spublish_range : null
             * readnum : 0
             * typename : 魔法阅读
             */

            private String id;
            private String sid;
            private String b_name;
            private String b_author;
            private String b_press;
            private String publish_time;
            private String b_cover;
            private String s_key;
            private String b_lead;
            private String b_sourceid;
            private String addtime;
            private String contract_addtime;
            private String contract_endtime;
            private String b_introduction;
            private Object cahepath;
            private String status;
            private String edittime;
            private String dowsnum;
            private Object spublish_range;
            private String readnum;
            private String typename;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getB_name() {
                return b_name;
            }

            public void setB_name(String b_name) {
                this.b_name = b_name;
            }

            public String getB_author() {
                return b_author;
            }

            public void setB_author(String b_author) {
                this.b_author = b_author;
            }

            public String getB_press() {
                return b_press;
            }

            public void setB_press(String b_press) {
                this.b_press = b_press;
            }

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public String getB_cover() {
                return b_cover;
            }

            public void setB_cover(String b_cover) {
                this.b_cover = b_cover;
            }

            public String getS_key() {
                return s_key;
            }

            public void setS_key(String s_key) {
                this.s_key = s_key;
            }

            public String getB_lead() {
                return b_lead;
            }

            public void setB_lead(String b_lead) {
                this.b_lead = b_lead;
            }

            public String getB_sourceid() {
                return b_sourceid;
            }

            public void setB_sourceid(String b_sourceid) {
                this.b_sourceid = b_sourceid;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getContract_addtime() {
                return contract_addtime;
            }

            public void setContract_addtime(String contract_addtime) {
                this.contract_addtime = contract_addtime;
            }

            public String getContract_endtime() {
                return contract_endtime;
            }

            public void setContract_endtime(String contract_endtime) {
                this.contract_endtime = contract_endtime;
            }

            public String getB_introduction() {
                return b_introduction;
            }

            public void setB_introduction(String b_introduction) {
                this.b_introduction = b_introduction;
            }

            public Object getCahepath() {
                return cahepath;
            }

            public void setCahepath(Object cahepath) {
                this.cahepath = cahepath;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getEdittime() {
                return edittime;
            }

            public void setEdittime(String edittime) {
                this.edittime = edittime;
            }

            public String getDowsnum() {
                return dowsnum;
            }

            public void setDowsnum(String dowsnum) {
                this.dowsnum = dowsnum;
            }

            public Object getSpublish_range() {
                return spublish_range;
            }

            public void setSpublish_range(Object spublish_range) {
                this.spublish_range = spublish_range;
            }

            public String getReadnum() {
                return readnum;
            }

            public void setReadnum(String readnum) {
                this.readnum = readnum;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }

        public static class _$1Bean {
            /**
             * id : 88
             * sid : 138
             * b_name : 三十六计
             * b_author : 佚名
             * b_press :
             * publish_time : 0
             * b_cover : null
             * s_key : 三十六计
             * b_lead :
             * b_sourceid : 1
             * addtime : 1546489111
             * contract_addtime : 0
             * contract_endtime : 0
             * b_introduction : 《三十六计》，中国古代三十六个兵法策略，或称三十六策，是指中国古代三十六个兵法策略，语源于南北朝，成书于明清。它是根据中国古代军事思想和丰富的斗争经验总结而成的兵书，是中华民族悠久非物质文化遗产之一。
             * cahepath : null
             * status : 3
             * edittime : 1547113213
             * dowsnum : 0
             * spublish_range : null
             * readnum : 0
             * typename : 人生修养
             */

            private String id;
            private String sid;
            private String b_name;
            private String b_author;
            private String b_press;
            private String publish_time;
            private Object b_cover;
            private String s_key;
            private String b_lead;
            private String b_sourceid;
            private String addtime;
            private String contract_addtime;
            private String contract_endtime;
            private String b_introduction;
            private Object cahepath;
            private String status;
            private String edittime;
            private String dowsnum;
            private Object spublish_range;
            private String readnum;
            private String typename;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getB_name() {
                return b_name;
            }

            public void setB_name(String b_name) {
                this.b_name = b_name;
            }

            public String getB_author() {
                return b_author;
            }

            public void setB_author(String b_author) {
                this.b_author = b_author;
            }

            public String getB_press() {
                return b_press;
            }

            public void setB_press(String b_press) {
                this.b_press = b_press;
            }

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public Object getB_cover() {
                return b_cover;
            }

            public void setB_cover(Object b_cover) {
                this.b_cover = b_cover;
            }

            public String getS_key() {
                return s_key;
            }

            public void setS_key(String s_key) {
                this.s_key = s_key;
            }

            public String getB_lead() {
                return b_lead;
            }

            public void setB_lead(String b_lead) {
                this.b_lead = b_lead;
            }

            public String getB_sourceid() {
                return b_sourceid;
            }

            public void setB_sourceid(String b_sourceid) {
                this.b_sourceid = b_sourceid;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getContract_addtime() {
                return contract_addtime;
            }

            public void setContract_addtime(String contract_addtime) {
                this.contract_addtime = contract_addtime;
            }

            public String getContract_endtime() {
                return contract_endtime;
            }

            public void setContract_endtime(String contract_endtime) {
                this.contract_endtime = contract_endtime;
            }

            public String getB_introduction() {
                return b_introduction;
            }

            public void setB_introduction(String b_introduction) {
                this.b_introduction = b_introduction;
            }

            public Object getCahepath() {
                return cahepath;
            }

            public void setCahepath(Object cahepath) {
                this.cahepath = cahepath;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getEdittime() {
                return edittime;
            }

            public void setEdittime(String edittime) {
                this.edittime = edittime;
            }

            public String getDowsnum() {
                return dowsnum;
            }

            public void setDowsnum(String dowsnum) {
                this.dowsnum = dowsnum;
            }

            public Object getSpublish_range() {
                return spublish_range;
            }

            public void setSpublish_range(Object spublish_range) {
                this.spublish_range = spublish_range;
            }

            public String getReadnum() {
                return readnum;
            }

            public void setReadnum(String readnum) {
                this.readnum = readnum;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }

        public static class _$2Bean {
            /**
             * id : 64
             * sid : 112
             * b_name : 唐诗三百首
             * b_author : 成涛
             * b_press :
             * publish_time : 0
             * b_cover : /20190103/tangshisanbaishou/META_INF/cover.jpg
             * s_key : 唐诗
             * b_lead :
             * b_sourceid : 1
             * addtime : 1546487454
             * contract_addtime : 0
             * contract_endtime : 0
             * b_introduction : 《唐诗三百首》是一部流传很广的唐诗选集。唐朝（618年~907年）二百八十九年间，是中国诗歌发展的黄金时代，云蒸霞蔚，名家辈出，唐诗数量多达五万余首。
             本书共选入唐代诗人77位，计311首诗，其中五言古诗33首，乐府46首，七言古诗28首，七言律诗50首，五言绝句29首，七言绝句51首，诸诗配有注释和评点。 五言古诗简称五古有，是唐代诗坛较为流行的体裁。唐人五古笔力豪纵，气象万千，直接用于叙事、抒情、议论、写景，使其功能得到了空前的发挥，其代表作家李白、杜甫、王维、孟浩然、韦应物等。
             * cahepath : null
             * status : 3
             * edittime : 1547110094
             * dowsnum : 0
             * spublish_range : null
             * readnum : 0
             * typename : 魔法阅读
             */

            private String id;
            private String sid;
            private String b_name;
            private String b_author;
            private String b_press;
            private String publish_time;
            private String b_cover;
            private String s_key;
            private String b_lead;
            private String b_sourceid;
            private String addtime;
            private String contract_addtime;
            private String contract_endtime;
            private String b_introduction;
            private Object cahepath;
            private String status;
            private String edittime;
            private String dowsnum;
            private Object spublish_range;
            private String readnum;
            private String typename;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getB_name() {
                return b_name;
            }

            public void setB_name(String b_name) {
                this.b_name = b_name;
            }

            public String getB_author() {
                return b_author;
            }

            public void setB_author(String b_author) {
                this.b_author = b_author;
            }

            public String getB_press() {
                return b_press;
            }

            public void setB_press(String b_press) {
                this.b_press = b_press;
            }

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public String getB_cover() {
                return b_cover;
            }

            public void setB_cover(String b_cover) {
                this.b_cover = b_cover;
            }

            public String getS_key() {
                return s_key;
            }

            public void setS_key(String s_key) {
                this.s_key = s_key;
            }

            public String getB_lead() {
                return b_lead;
            }

            public void setB_lead(String b_lead) {
                this.b_lead = b_lead;
            }

            public String getB_sourceid() {
                return b_sourceid;
            }

            public void setB_sourceid(String b_sourceid) {
                this.b_sourceid = b_sourceid;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getContract_addtime() {
                return contract_addtime;
            }

            public void setContract_addtime(String contract_addtime) {
                this.contract_addtime = contract_addtime;
            }

            public String getContract_endtime() {
                return contract_endtime;
            }

            public void setContract_endtime(String contract_endtime) {
                this.contract_endtime = contract_endtime;
            }

            public String getB_introduction() {
                return b_introduction;
            }

            public void setB_introduction(String b_introduction) {
                this.b_introduction = b_introduction;
            }

            public Object getCahepath() {
                return cahepath;
            }

            public void setCahepath(Object cahepath) {
                this.cahepath = cahepath;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getEdittime() {
                return edittime;
            }

            public void setEdittime(String edittime) {
                this.edittime = edittime;
            }

            public String getDowsnum() {
                return dowsnum;
            }

            public void setDowsnum(String dowsnum) {
                this.dowsnum = dowsnum;
            }

            public Object getSpublish_range() {
                return spublish_range;
            }

            public void setSpublish_range(Object spublish_range) {
                this.spublish_range = spublish_range;
            }

            public String getReadnum() {
                return readnum;
            }

            public void setReadnum(String readnum) {
                this.readnum = readnum;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }
    }
}
