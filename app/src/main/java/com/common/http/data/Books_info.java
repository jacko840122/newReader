package com.common.http.data;

import java.util.List;

public class Books_info{


    /**
     * success : true
     * message : 成功
     * data : [{"id":"92","sid":"143","b_name":"三国演义","b_author":"罗贯中","b_lead":"","b_cover":"0","b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//sanguoyanyi.epub","catalog":[{"cname":"第001回　宴桃园豪杰三结义　斩黄巾英雄首立功"},{"cname":"第002回　张翼德怒鞭督邮　何国舅谋诛宦竖"},{"cname":"第003回　议温明董卓叱丁原　馈金珠李肃说吕布"},{"cname":"第004回　废汉帝陈留践位　谋董贼孟德献刀"},{"cname":"第005回　发矫诏诸镇应曹公　破关兵三英战吕布"},{"cname":"第006回　焚金阙董卓行凶　匿玉玺孙坚背约"},{"cname":"第007回　袁绍磐河战公孙　孙坚跨江击刘表"},{"cname":"第008回　王司徒巧使连环计　董太师大闹凤仪亭"},{"cname":"第009回　除暴凶吕布助司徒　犯长安李傕听贾诩"},{"cname":"第010回　勤王室马腾举义　报父仇曹操兴师"},{"cname":"第011回　刘皇叔北海救孔融　吕温侯濮阳破曹操"},{"cname":"第012回　陶恭祖三让徐州　曹孟穗大战吕布"},{"cname":"第013回　李傕郭汜大交兵　杨奉董承双救驾"},{"cname":"第014回　曹孟德移驾幸许都　吕奉先乘夜袭徐郡"},{"cname":"第015回　太史慈酣斗小霸王　孙伯符大战严白虎"},{"cname":"第016回　吕奉先射戟辕门　曹孟德败师淯水"},{"cname":"第017回　袁公路大起七军　曹孟德会合三将"},{"cname":"第018回　贾文和料敌决胜　夏侯惇拨矢啖睛"},{"cname":"第019回　下邳城曹操鏖兵　白门楼吕布殒命"},{"cname":"第020回　曹阿瞒许田打围　董国舅内阁受诏"},{"cname":"第021回　曹操煮酒论英雄　关公赚城斩车胄"},{"cname":"第022回　袁曹各起马步三军　关张共擒王刘二将"},{"cname":"第023回　祢正平裸衣骂贼　吉太医下毒遭刑"},{"cname":"第024回　国贼行凶杀贵妃　皇叔败走投袁绍"},{"cname":"第025回　屯土山关公约三事　救白马曹操解重围"},{"cname":"第026回　袁本初败兵折将　关云长挂印封金"},{"cname":"第027回　美髯公千里走单骑　汉寿侯五关斩六将"},{"cname":"第028回　斩蔡阳兄弟释疑　会古城主臣聚义"},{"cname":"第029回　小霸王怒斩于吉　碧眼儿坐领江东"},{"cname":"第030回　战官渡本初败绩　劫乌巢孟德烧粮"},{"cname":"第031回　曹操仓亭破本初　玄德荆州依刘表"},{"cname":"第032回　夺冀州袁尚争锋　决漳河许攸献计"},{"cname":"第033回　曹丕乘乱纳甄氏　郭嘉遗计定辽东"},{"cname":"第034回　蔡夫人隔屏听密语　刘皇叔跃马过檀溪"},{"cname":"第035回　玄德南漳逢隐沧　单福新野遇英主"},{"cname":"第036回　玄德用计袭樊城　元直走马荐诸葛"},{"cname":"第037回　司马徽再荐名士　刘玄德三顾草庐"},{"cname":"第038回　定三分隆中决策　战长江孙氏报仇"},{"cname":"第039回　荆州城公子三求计　博望坡军师初用兵"},{"cname":"第040回　蔡夫人议献荆州　诸葛亮火烧新野"},{"cname":"第041回　刘玄德携民渡江　赵子龙单骑救主"},{"cname":"第042回　张翼德大闹长坂桥　刘豫州败走汉津口"},{"cname":"第043回　诸葛亮舌战群儒　鲁子敬力排众议"},{"cname":"第044回　孔明用智激周瑜　孙权决计破曹操"},{"cname":"第045回　三江口曹操折兵　群英会蒋干中计"},{"cname":"第046回　用奇谋孔明借箭　献密计黄盖受刑"},{"cname":"第047回　阚泽密献诈降书　庞统巧授连环计"},{"cname":"第048回　宴长江曹操赋诗　锁战船北军用武"},{"cname":"第049回　七星坛诸葛祭风　三江口周瑜纵火"},{"cname":"第050回　诸葛亮智算华容　关云长义释曹操"},{"cname":"第051回　曹仁大战东吴兵　孔明一气周公瑾"},{"cname":"第052回　诸葛亮智辞鲁肃　赵子龙计取桂阳"},{"cname":"第053回　关云长义释黄汉升　孙仲谋大战张文远"},{"cname":"第054回　吴国太佛寺看新郎　刘皇叔洞房续佳偶"},{"cname":"第055回　玄德智激孙夫人　孔明二气周公瑾"},{"cname":"第056回　曹操大宴铜雀台　孔明三气周公瑾"},{"cname":"第057回　柴桑口卧龙吊丧　耒阳县凤雏理事"},{"cname":"第058回　马孟起兴兵雪恨　曹阿瞒割须弃袍"},{"cname":"第059回　许诸裸衣斗马超　曹操抹书问韩遂"},{"cname":"第060回　张永年反难杨修　庞士元议取西蜀"},{"cname":"第061回　赵云截江夺阿斗　孙权遗书退老瞒"},{"cname":"第062回　取涪关杨高授首　攻雒城黄魏争功"},{"cname":"第063回　诸葛亮痛哭庞统　张翼德义释严颜"},{"cname":"第064回　孔明定计捉张任　杨阜借兵破马超"},{"cname":"第065回　马超大战葭萌关　刘备自领益州牧"},{"cname":"第066回　关云长单刀赴会　伏皇后为国捐生"},{"cname":"第067回　曹操平定汉中地　张辽威震逍遥津"},{"cname":"第068回　甘宁百骑劫魏营　左慈掷杯戏曹操"},{"cname":"第069回　卜周易管辂知机　讨汉贼五臣死节"},{"cname":"第070回　猛张飞智取瓦口隘　老黄忠计夺天荡山"},{"cname":"第071回　占对山黄忠逸待劳　据汉水赵云寡胜众"},{"cname":"第072回　诸葛亮智取汉中　曹阿瞒兵退斜谷"},{"cname":"第073回　玄德进位汉中王　云长攻拔襄阳郡"},{"cname":"第074回　庞令明抬榇决死战　关云长放水淹七军"},{"cname":"第075回　关云长刮骨疗毒　吕子明白衣渡江"},{"cname":"第076回　徐公明大战沔水　关云长败走麦城"},{"cname":"第077回　玉泉山关公显圣　洛阳城曹操感神"},{"cname":"第078回　治风疾神医身死　传遗命奸雄数终"},{"cname":"第079回　兄逼弟曹植赋诗　侄陷叔刘封伏法"},{"cname":"第080回　曹丕废帝篡炎刘　汉王正位续大统"},{"cname":"第081回　急兄仇张飞遇害　　雪弟恨先主兴兵"},{"cname":"第082回　孙权降魏受九锡　先主征吴赏六军"},{"cname":"第083回　战猇亭先主得仇人　守江口书生拜大将"},{"cname":"第084回　陆逊营烧七百里　孔明巧布八阵图"},{"cname":"第085回　刘先主遗诏托孤儿　诸葛亮安居平五路"},{"cname":"第086回　难张温秦宓逞天辩　破曹丕徐盛用火攻"},{"cname":"第087回　征南寇丞相大兴师　抗天兵蛮王初受执"},{"cname":"第088回　渡泸水再缚番王　识诈降三擒孟获"},{"cname":"第089回　武乡侯四番用计　南蛮王五次遭擒"},{"cname":"第090回　驱巨善六破蛮兵　烧藤甲七擒孟获"},{"cname":"第091回　祭泸水汉相班师　伐中原武侯上表"},{"cname":"第092回　赵子龙力斩五将　诸葛亮智取三城"},{"cname":"第093回　姜伯约归降孔明　武乡侯骂死王朝"},{"cname":"第094回　诸葛亮乘雪破羌兵　司马懿克日擒孟达"},{"cname":"第095回　马谡拒谏失街亭　武侯弹琴退仲达"},{"cname":"第096回　孔明挥泪斩马谡　周鲂断发赚曹休"},{"cname":"第097回　讨魏国武侯再上表　破曹兵姜维诈献书"},{"cname":"第098回　追汉军王双受诛　袭陈仓武侯取胜"},{"cname":"第099回　诸葛亮大破魏兵　司马懿入寇西蜀"},{"cname":"第100回　汉兵劫寨破曹真　武侯斗阵辱仲达"},{"cname":"第101回　出陇上诸葛妆神　奔剑阁张郃中计"},{"cname":"第102回　司马懿占北原渭桥　诸葛亮造木牛流马"},{"cname":"第103回　上方谷司马受困　五丈原诸葛禳星"},{"cname":"第104回　陨大星汉丞相归天　见木像魏都督丧胆"},{"cname":"第105回　武侯预伏锦囊计　魏主拆取承露盘"},{"cname":"第106回　公孙渊兵败死襄平　司马懿诈病赚曹爽"},{"cname":"第107回　魏主政归司马氏　姜维兵败牛头山"},{"cname":"第108回　丁奉雪中奋短兵　孙峻席间施密计"},{"cname":"第109回　困司马汉将奇谋　废曹芳魏家果报"},{"cname":"第110回　文鸯单骑退雄兵　姜维背水破大敌"},{"cname":"第111回　邓士载智败姜伯约　诸葛诞义讨司马昭"},{"cname":"第112回　救寿春于诠死节　取长城伯约鏖兵"},{"cname":"第113回　丁奉定计斩孙綝　姜维斗阵破邓艾"},{"cname":"第114回　曹髦驱车死南阙　姜维弃粮胜魏兵"},{"cname":"第115回　诏班师后主信谗　托屯田姜维避祸"},{"cname":"第116回　钟会分兵汉中道　武侯显圣定军山"},{"cname":"第117回　邓士载偷度阴平　诸葛瞻战死绵竹"},{"cname":"第118回　哭祖庙一王死孝　入西川二士争功"},{"cname":"第119回　假投降巧计成虚话　再受禅依样画葫芦"},{"cname":"第120回　荐杜预老将献新谋　降孙皓三分归一统"}],"size":"0.93M","feelnum":"1"}]
     * status : 200
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
         * id : 92
         * sid : 143
         * b_name : 三国演义
         * b_author : 罗贯中
         * b_lead :
         * b_cover : 0
         * b_introduction :
         * readnum : 0
         * typename : 文学经典
         * url : http://47.104.171.87/content/20190103//sanguoyanyi.epub
         * catalog : [{"cname":"第001回　宴桃园豪杰三结义　斩黄巾英雄首立功"},{"cname":"第002回　张翼德怒鞭督邮　何国舅谋诛宦竖"},{"cname":"第003回　议温明董卓叱丁原　馈金珠李肃说吕布"},{"cname":"第004回　废汉帝陈留践位　谋董贼孟德献刀"},{"cname":"第005回　发矫诏诸镇应曹公　破关兵三英战吕布"},{"cname":"第006回　焚金阙董卓行凶　匿玉玺孙坚背约"},{"cname":"第007回　袁绍磐河战公孙　孙坚跨江击刘表"},{"cname":"第008回　王司徒巧使连环计　董太师大闹凤仪亭"},{"cname":"第009回　除暴凶吕布助司徒　犯长安李傕听贾诩"},{"cname":"第010回　勤王室马腾举义　报父仇曹操兴师"},{"cname":"第011回　刘皇叔北海救孔融　吕温侯濮阳破曹操"},{"cname":"第012回　陶恭祖三让徐州　曹孟穗大战吕布"},{"cname":"第013回　李傕郭汜大交兵　杨奉董承双救驾"},{"cname":"第014回　曹孟德移驾幸许都　吕奉先乘夜袭徐郡"},{"cname":"第015回　太史慈酣斗小霸王　孙伯符大战严白虎"},{"cname":"第016回　吕奉先射戟辕门　曹孟德败师淯水"},{"cname":"第017回　袁公路大起七军　曹孟德会合三将"},{"cname":"第018回　贾文和料敌决胜　夏侯惇拨矢啖睛"},{"cname":"第019回　下邳城曹操鏖兵　白门楼吕布殒命"},{"cname":"第020回　曹阿瞒许田打围　董国舅内阁受诏"},{"cname":"第021回　曹操煮酒论英雄　关公赚城斩车胄"},{"cname":"第022回　袁曹各起马步三军　关张共擒王刘二将"},{"cname":"第023回　祢正平裸衣骂贼　吉太医下毒遭刑"},{"cname":"第024回　国贼行凶杀贵妃　皇叔败走投袁绍"},{"cname":"第025回　屯土山关公约三事　救白马曹操解重围"},{"cname":"第026回　袁本初败兵折将　关云长挂印封金"},{"cname":"第027回　美髯公千里走单骑　汉寿侯五关斩六将"},{"cname":"第028回　斩蔡阳兄弟释疑　会古城主臣聚义"},{"cname":"第029回　小霸王怒斩于吉　碧眼儿坐领江东"},{"cname":"第030回　战官渡本初败绩　劫乌巢孟德烧粮"},{"cname":"第031回　曹操仓亭破本初　玄德荆州依刘表"},{"cname":"第032回　夺冀州袁尚争锋　决漳河许攸献计"},{"cname":"第033回　曹丕乘乱纳甄氏　郭嘉遗计定辽东"},{"cname":"第034回　蔡夫人隔屏听密语　刘皇叔跃马过檀溪"},{"cname":"第035回　玄德南漳逢隐沧　单福新野遇英主"},{"cname":"第036回　玄德用计袭樊城　元直走马荐诸葛"},{"cname":"第037回　司马徽再荐名士　刘玄德三顾草庐"},{"cname":"第038回　定三分隆中决策　战长江孙氏报仇"},{"cname":"第039回　荆州城公子三求计　博望坡军师初用兵"},{"cname":"第040回　蔡夫人议献荆州　诸葛亮火烧新野"},{"cname":"第041回　刘玄德携民渡江　赵子龙单骑救主"},{"cname":"第042回　张翼德大闹长坂桥　刘豫州败走汉津口"},{"cname":"第043回　诸葛亮舌战群儒　鲁子敬力排众议"},{"cname":"第044回　孔明用智激周瑜　孙权决计破曹操"},{"cname":"第045回　三江口曹操折兵　群英会蒋干中计"},{"cname":"第046回　用奇谋孔明借箭　献密计黄盖受刑"},{"cname":"第047回　阚泽密献诈降书　庞统巧授连环计"},{"cname":"第048回　宴长江曹操赋诗　锁战船北军用武"},{"cname":"第049回　七星坛诸葛祭风　三江口周瑜纵火"},{"cname":"第050回　诸葛亮智算华容　关云长义释曹操"},{"cname":"第051回　曹仁大战东吴兵　孔明一气周公瑾"},{"cname":"第052回　诸葛亮智辞鲁肃　赵子龙计取桂阳"},{"cname":"第053回　关云长义释黄汉升　孙仲谋大战张文远"},{"cname":"第054回　吴国太佛寺看新郎　刘皇叔洞房续佳偶"},{"cname":"第055回　玄德智激孙夫人　孔明二气周公瑾"},{"cname":"第056回　曹操大宴铜雀台　孔明三气周公瑾"},{"cname":"第057回　柴桑口卧龙吊丧　耒阳县凤雏理事"},{"cname":"第058回　马孟起兴兵雪恨　曹阿瞒割须弃袍"},{"cname":"第059回　许诸裸衣斗马超　曹操抹书问韩遂"},{"cname":"第060回　张永年反难杨修　庞士元议取西蜀"},{"cname":"第061回　赵云截江夺阿斗　孙权遗书退老瞒"},{"cname":"第062回　取涪关杨高授首　攻雒城黄魏争功"},{"cname":"第063回　诸葛亮痛哭庞统　张翼德义释严颜"},{"cname":"第064回　孔明定计捉张任　杨阜借兵破马超"},{"cname":"第065回　马超大战葭萌关　刘备自领益州牧"},{"cname":"第066回　关云长单刀赴会　伏皇后为国捐生"},{"cname":"第067回　曹操平定汉中地　张辽威震逍遥津"},{"cname":"第068回　甘宁百骑劫魏营　左慈掷杯戏曹操"},{"cname":"第069回　卜周易管辂知机　讨汉贼五臣死节"},{"cname":"第070回　猛张飞智取瓦口隘　老黄忠计夺天荡山"},{"cname":"第071回　占对山黄忠逸待劳　据汉水赵云寡胜众"},{"cname":"第072回　诸葛亮智取汉中　曹阿瞒兵退斜谷"},{"cname":"第073回　玄德进位汉中王　云长攻拔襄阳郡"},{"cname":"第074回　庞令明抬榇决死战　关云长放水淹七军"},{"cname":"第075回　关云长刮骨疗毒　吕子明白衣渡江"},{"cname":"第076回　徐公明大战沔水　关云长败走麦城"},{"cname":"第077回　玉泉山关公显圣　洛阳城曹操感神"},{"cname":"第078回　治风疾神医身死　传遗命奸雄数终"},{"cname":"第079回　兄逼弟曹植赋诗　侄陷叔刘封伏法"},{"cname":"第080回　曹丕废帝篡炎刘　汉王正位续大统"},{"cname":"第081回　急兄仇张飞遇害　　雪弟恨先主兴兵"},{"cname":"第082回　孙权降魏受九锡　先主征吴赏六军"},{"cname":"第083回　战猇亭先主得仇人　守江口书生拜大将"},{"cname":"第084回　陆逊营烧七百里　孔明巧布八阵图"},{"cname":"第085回　刘先主遗诏托孤儿　诸葛亮安居平五路"},{"cname":"第086回　难张温秦宓逞天辩　破曹丕徐盛用火攻"},{"cname":"第087回　征南寇丞相大兴师　抗天兵蛮王初受执"},{"cname":"第088回　渡泸水再缚番王　识诈降三擒孟获"},{"cname":"第089回　武乡侯四番用计　南蛮王五次遭擒"},{"cname":"第090回　驱巨善六破蛮兵　烧藤甲七擒孟获"},{"cname":"第091回　祭泸水汉相班师　伐中原武侯上表"},{"cname":"第092回　赵子龙力斩五将　诸葛亮智取三城"},{"cname":"第093回　姜伯约归降孔明　武乡侯骂死王朝"},{"cname":"第094回　诸葛亮乘雪破羌兵　司马懿克日擒孟达"},{"cname":"第095回　马谡拒谏失街亭　武侯弹琴退仲达"},{"cname":"第096回　孔明挥泪斩马谡　周鲂断发赚曹休"},{"cname":"第097回　讨魏国武侯再上表　破曹兵姜维诈献书"},{"cname":"第098回　追汉军王双受诛　袭陈仓武侯取胜"},{"cname":"第099回　诸葛亮大破魏兵　司马懿入寇西蜀"},{"cname":"第100回　汉兵劫寨破曹真　武侯斗阵辱仲达"},{"cname":"第101回　出陇上诸葛妆神　奔剑阁张郃中计"},{"cname":"第102回　司马懿占北原渭桥　诸葛亮造木牛流马"},{"cname":"第103回　上方谷司马受困　五丈原诸葛禳星"},{"cname":"第104回　陨大星汉丞相归天　见木像魏都督丧胆"},{"cname":"第105回　武侯预伏锦囊计　魏主拆取承露盘"},{"cname":"第106回　公孙渊兵败死襄平　司马懿诈病赚曹爽"},{"cname":"第107回　魏主政归司马氏　姜维兵败牛头山"},{"cname":"第108回　丁奉雪中奋短兵　孙峻席间施密计"},{"cname":"第109回　困司马汉将奇谋　废曹芳魏家果报"},{"cname":"第110回　文鸯单骑退雄兵　姜维背水破大敌"},{"cname":"第111回　邓士载智败姜伯约　诸葛诞义讨司马昭"},{"cname":"第112回　救寿春于诠死节　取长城伯约鏖兵"},{"cname":"第113回　丁奉定计斩孙綝　姜维斗阵破邓艾"},{"cname":"第114回　曹髦驱车死南阙　姜维弃粮胜魏兵"},{"cname":"第115回　诏班师后主信谗　托屯田姜维避祸"},{"cname":"第116回　钟会分兵汉中道　武侯显圣定军山"},{"cname":"第117回　邓士载偷度阴平　诸葛瞻战死绵竹"},{"cname":"第118回　哭祖庙一王死孝　入西川二士争功"},{"cname":"第119回　假投降巧计成虚话　再受禅依样画葫芦"},{"cname":"第120回　荐杜预老将献新谋　降孙皓三分归一统"}]
         * size : 0.93M
         * feelnum : 1
         */

        private String id;
        private String sid;
        private String b_name;
        private String b_author;
        private String b_lead;
        private String b_cover;
        private String b_introduction;
        private String readnum;
        private String typename;
        private String url;
        private String size;
        private String feelnum;
        private List<CatalogBean> catalog;

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

        public String getB_lead() {
            return b_lead;
        }

        public void setB_lead(String b_lead) {
            this.b_lead = b_lead;
        }

        public String getB_cover() {
            return b_cover;
        }

        public void setB_cover(String b_cover) {
            this.b_cover = b_cover;
        }

        public String getB_introduction() {
            return b_introduction;
        }

        public void setB_introduction(String b_introduction) {
            this.b_introduction = b_introduction;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getFeelnum() {
            return feelnum;
        }

        public void setFeelnum(String feelnum) {
            this.feelnum = feelnum;
        }

        public List<CatalogBean> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<CatalogBean> catalog) {
            this.catalog = catalog;
        }

        public static class CatalogBean {
            /**
             * cname : 第001回　宴桃园豪杰三结义　斩黄巾英雄首立功
             */

            private String cname;

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }
        }
    }
}
