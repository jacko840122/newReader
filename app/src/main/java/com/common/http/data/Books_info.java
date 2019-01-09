package com.common.http.data;

import java.util.List;

public class Books_info{

    /**
     * success : true
     * message : 成功
     * data : [{"id":"81","sid":"131","b_name":"科学改变人类生活的100个瞬间","b_author":"路甬祥","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"科学技术","url":"http://47.104.171.87/content/20190103//kexuegaibianrenleishenghuode100geshunjian.txt","catalog":[],"size":"0.01M","feelnum":"0"},{"id":"60","sid":"108","b_name":"安徒生童话","b_author":"安徒生","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//antushengtonghua.txt","catalog":[],"size":"1.70M","feelnum":"0"},{"id":"61","sid":"109","b_name":"数理化通俗演义","b_author":"梁衡","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//shulihuatongsuyanyi.txt","catalog":[],"size":"0.62M","feelnum":"0"},{"id":"62","sid":"110","b_name":"孔子的故事","b_author":"李长之","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//kongzidegushi.txt","catalog":[],"size":"0.02M","feelnum":"0"},{"id":"63","sid":"111","b_name":"古文观止","b_author":"吴楚材 吴调侯","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//guwenguanzhi.txt","catalog":[],"size":"1.24M","feelnum":"0"},{"id":"64","sid":"112","b_name":"唐诗三百首","b_author":"成涛","b_lead":"","b_cover":"/20190103/tangshisanbaishou/META_INF/cover.jpg","b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//tangshisanbaishou.epub","catalog":"main-css,chapter1,chapter2,chapter3,chapter4,chapter5,chapter6,chapter7,chapter8,chapter9,chapter10,chapter11,ncx,cover-image,","size":"0.32M","feelnum":"0"},{"id":"65","sid":"113","b_name":"蒋勋说唐诗","b_author":"蒋勋说唐诗","b_lead":"","b_cover":"/20190103/jiangxunshuotangshi/META_INF/cover.jpg","b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//jiangxunshuotangshi.epub","catalog":"蒋勋说唐诗,第一章\t大唐盛世,第一节\t诗像一粒珍珠,第二节\t唐代是诗的盛世,第三节\t新绣罗裙两面红，一面狮子一面龙,第四节\t菩提萨埵与水到渠成,第五节\t文学的内容与形式,第六节\t前不见古人，后不见来者,第七节\t诗人的孤独感,第八节\t游牧民族的华丽,第九节\t唐诗里的残酷,第十节\t侠的精神,第十一节 唐朝是一场精彩的戏,第二章 李白（上）,第一节\t诗歌的传统与创新,第二节\t角色转换,第三节\t青梅竹马,第四节\t十四为君妇,第五节\t定格,第六节\t《蜀道难》,第七节\t其险也若此,第八节\t浪漫诗的极致,第三章 杜甫（上）,第一节\t梦李白,第二节\t长安水边多丽人,第三节\t《兵车行》,第四节\t《石壕吏》,第四章 李商隐（上）,第一节\t唯美的回忆,第二节\t幻灭与眷恋的纠缠,第三节\t繁华的沉淀,第四节\t抽象与象征,第五节\t想见时难别亦难,第六节\t花下醉,第七节\t人间重晚晴,","size":"0.24M","feelnum":"0"},{"id":"66","sid":"114","b_name":"毛泽东诗词全集","b_author":"毛泽东","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//maozedongshiciquanji.txt","catalog":[],"size":"0.03M","feelnum":"0"},{"id":"67","sid":"115","b_name":"老人与海","b_author":"海明威","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//laorenyuhai.txt","catalog":[],"size":"0.09M","feelnum":"0"},{"id":"68","sid":"116","b_name":"红楼梦","b_author":"曹雪芹","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//hongloumeng.txt","catalog":[],"size":"3.37M","feelnum":"0"},{"id":"69","sid":"117","b_name":"西游记","b_author":"吴承恩","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//xiyouji.txt","catalog":[],"size":"1.24M","feelnum":"0"},{"id":"92","sid":"143","b_name":"三国演义","b_author":"罗贯中","b_lead":"","b_cover":"0","b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//sanguoyanyi.epub","catalog":"第001回　宴桃园豪杰三结义　斩黄巾英雄首立功,第002回　张翼德怒鞭督邮　何国舅谋诛宦竖,第003回　议温明董卓叱丁原　馈金珠李肃说吕布,第004回　废汉帝陈留践位　谋董贼孟德献刀,第005回　发矫诏诸镇应曹公　破关兵三英战吕布,第006回　焚金阙董卓行凶　匿玉玺孙坚背约,第007回　袁绍磐河战公孙　孙坚跨江击刘表,第008回　王司徒巧使连环计　董太师大闹凤仪亭,第009回　除暴凶吕布助司徒　犯长安李傕听贾诩,第010回　勤王室马腾举义　报父仇曹操兴师,第011回　刘皇叔北海救孔融　吕温侯濮阳破曹操,第012回　陶恭祖三让徐州　曹孟穗大战吕布,第013回　李傕郭汜大交兵　杨奉董承双救驾,第014回　曹孟德移驾幸许都　吕奉先乘夜袭徐郡,第015回　太史慈酣斗小霸王　孙伯符大战严白虎,第016回　吕奉先射戟辕门　曹孟德败师淯水,第017回　袁公路大起七军　曹孟德会合三将,第018回　贾文和料敌决胜　夏侯惇拨矢啖睛,第019回　下邳城曹操鏖兵　白门楼吕布殒命,第020回　曹阿瞒许田打围　董国舅内阁受诏,第021回　曹操煮酒论英雄　关公赚城斩车胄,第022回　袁曹各起马步三军　关张共擒王刘二将,第023回　祢正平裸衣骂贼　吉太医下毒遭刑,第024回　国贼行凶杀贵妃　皇叔败走投袁绍,第025回　屯土山关公约三事　救白马曹操解重围,第026回　袁本初败兵折将　关云长挂印封金,第027回　美髯公千里走单骑　汉寿侯五关斩六将,第028回　斩蔡阳兄弟释疑　会古城主臣聚义,第029回　小霸王怒斩于吉　碧眼儿坐领江东,第030回　战官渡本初败绩　劫乌巢孟德烧粮,第031回　曹操仓亭破本初　玄德荆州依刘表,第032回　夺冀州袁尚争锋　决漳河许攸献计,第033回　曹丕乘乱纳甄氏　郭嘉遗计定辽东,第034回　蔡夫人隔屏听密语　刘皇叔跃马过檀溪,第035回　玄德南漳逢隐沧　单福新野遇英主,第036回　玄德用计袭樊城　元直走马荐诸葛,第037回　司马徽再荐名士　刘玄德三顾草庐,第038回　定三分隆中决策　战长江孙氏报仇,第039回　荆州城公子三求计　博望坡军师初用兵,第040回　蔡夫人议献荆州　诸葛亮火烧新野,第041回　刘玄德携民渡江　赵子龙单骑救主,第042回　张翼德大闹长坂桥　刘豫州败走汉津口,第043回　诸葛亮舌战群儒　鲁子敬力排众议,第044回　孔明用智激周瑜　孙权决计破曹操,第045回　三江口曹操折兵　群英会蒋干中计,第046回　用奇谋孔明借箭　献密计黄盖受刑,第047回　阚泽密献诈降书　庞统巧授连环计,第048回　宴长江曹操赋诗　锁战船北军用武,第049回　七星坛诸葛祭风　三江口周瑜纵火,第050回　诸葛亮智算华容　关云长义释曹操,第051回　曹仁大战东吴兵　孔明一气周公瑾,第052回　诸葛亮智辞鲁肃　赵子龙计取桂阳,第053回　关云长义释黄汉升　孙仲谋大战张文远,第054回　吴国太佛寺看新郎　刘皇叔洞房续佳偶,第055回　玄德智激孙夫人　孔明二气周公瑾,第056回　曹操大宴铜雀台　孔明三气周公瑾,第057回　柴桑口卧龙吊丧　耒阳县凤雏理事,第058回　马孟起兴兵雪恨　曹阿瞒割须弃袍,第059回　许诸裸衣斗马超　曹操抹书问韩遂,第060回　张永年反难杨修　庞士元议取西蜀,第061回　赵云截江夺阿斗　孙权遗书退老瞒,第062回　取涪关杨高授首　攻雒城黄魏争功,第063回　诸葛亮痛哭庞统　张翼德义释严颜,第064回　孔明定计捉张任　杨阜借兵破马超,第065回　马超大战葭萌关　刘备自领益州牧,第066回　关云长单刀赴会　伏皇后为国捐生,第067回　曹操平定汉中地　张辽威震逍遥津,第068回　甘宁百骑劫魏营　左慈掷杯戏曹操,第069回　卜周易管辂知机　讨汉贼五臣死节,第070回　猛张飞智取瓦口隘　老黄忠计夺天荡山,第071回　占对山黄忠逸待劳　据汉水赵云寡胜众,第072回　诸葛亮智取汉中　曹阿瞒兵退斜谷,第073回　玄德进位汉中王　云长攻拔襄阳郡,第074回　庞令明抬榇决死战　关云长放水淹七军,第075回　关云长刮骨疗毒　吕子明白衣渡江,第076回　徐公明大战沔水　关云长败走麦城,第077回　玉泉山关公显圣　洛阳城曹操感神,第078回　治风疾神医身死　传遗命奸雄数终,第079回　兄逼弟曹植赋诗　侄陷叔刘封伏法,第080回　曹丕废帝篡炎刘　汉王正位续大统,第081回　急兄仇张飞遇害　　雪弟恨先主兴兵,第082回　孙权降魏受九锡　先主征吴赏六军,第083回　战猇亭先主得仇人　守江口书生拜大将,第084回　陆逊营烧七百里　孔明巧布八阵图,第085回　刘先主遗诏托孤儿　诸葛亮安居平五路,第086回　难张温秦宓逞天辩　破曹丕徐盛用火攻,第087回　征南寇丞相大兴师　抗天兵蛮王初受执,第088回　渡泸水再缚番王　识诈降三擒孟获,第089回　武乡侯四番用计　南蛮王五次遭擒,第090回　驱巨善六破蛮兵　烧藤甲七擒孟获,第091回　祭泸水汉相班师　伐中原武侯上表,第092回　赵子龙力斩五将　诸葛亮智取三城,第093回　姜伯约归降孔明　武乡侯骂死王朝,第094回　诸葛亮乘雪破羌兵　司马懿克日擒孟达,第095回　马谡拒谏失街亭　武侯弹琴退仲达,第096回　孔明挥泪斩马谡　周鲂断发赚曹休,第097回　讨魏国武侯再上表　破曹兵姜维诈献书,第098回　追汉军王双受诛　袭陈仓武侯取胜,第099回　诸葛亮大破魏兵　司马懿入寇西蜀,第100回　汉兵劫寨破曹真　武侯斗阵辱仲达,第101回　出陇上诸葛妆神　奔剑阁张郃中计,第102回　司马懿占北原渭桥　诸葛亮造木牛流马,第103回　上方谷司马受困　五丈原诸葛禳星,第104回　陨大星汉丞相归天　见木像魏都督丧胆,第105回　武侯预伏锦囊计　魏主拆取承露盘,第106回　公孙渊兵败死襄平　司马懿诈病赚曹爽,第107回　魏主政归司马氏　姜维兵败牛头山,第108回　丁奉雪中奋短兵　孙峻席间施密计,第109回　困司马汉将奇谋　废曹芳魏家果报,第110回　文鸯单骑退雄兵　姜维背水破大敌,第111回　邓士载智败姜伯约　诸葛诞义讨司马昭,第112回　救寿春于诠死节　取长城伯约鏖兵,第113回　丁奉定计斩孙綝　姜维斗阵破邓艾,第114回　曹髦驱车死南阙　姜维弃粮胜魏兵,第115回　诏班师后主信谗　托屯田姜维避祸,第116回　钟会分兵汉中道　武侯显圣定军山,第117回　邓士载偷度阴平　诸葛瞻战死绵竹,第118回　哭祖庙一王死孝　入西川二士争功,第119回　假投降巧计成虚话　再受禅依样画葫芦,第120回　荐杜预老将献新谋　降孙皓三分归一统,","size":"0.93M","feelnum":"1"},{"id":"71","sid":"119","b_name":"水浒传","b_author":"施耐庵","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//shuichuan.txt","catalog":[],"size":"1.76M","feelnum":"0"},{"id":"72","sid":"122","b_name":"平凡的世界","b_author":"路遥","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//pingfandeshijie.txt","catalog":[],"size":"2.31M","feelnum":"0"},{"id":"73","sid":"123","b_name":"泰戈尔诗选","b_author":"泰戈尔","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//taigeershixuan.txt","catalog":[],"size":"0.39M","feelnum":"0"},{"id":"74","sid":"124","b_name":"射雕英雄传","b_author":"金庸","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//shediaoyingxiongchuan.txt","catalog":[],"size":"2.60M","feelnum":"0"},{"id":"75","sid":"125","b_name":"围城","b_author":"钱钟书","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//weicheng.txt","catalog":[],"size":"0.64M","feelnum":"0"},{"id":"76","sid":"126","b_name":"教父","b_author":"马里奥 普佐","b_lead":"","b_cover":"0","b_introduction":"","readnum":"0","typename":"文学经典","url":"http://47.104.171.87/content/20190103//jiaofu.epub","catalog":"教父,第二章,第三章,第四章,第五章,第六章,第七章,第八章,第九章,第十章,第十一章,第二部,第十三章,第三部,第四部,第十六章,第十七章,第十八章,第十九章,第五部,第二十一章,第二十二章,第六部,第二十四章,第七部,第二十六章,第二十七章,第二十八章,第八部,第三十章,第三十一章,第九部,备注,教父Ⅱ：西西里人,第一部,第二部,第三章,第四章,第五章,第六章,第七章,第八章,第九章,第十章,第十一章,第十二章,第十三章,第十四章,第十五章,第三部,第十七章,第四部,第十九章,第二十章,第二十一章,第二十二章,第二十三章,第二十四章,第五部,第二十六章,第二十七章,第二十八章,第二十九章,第三十章,第三十一章,备注,教父Ⅲ：最后的教父,序 幕,第一部,第二章,第二部,第三部,第五章,第四部,第五部,第八章,第九章,第六部,第十一章,第十二章,第十三章,第十四章,第十五章,第十六章,第十七章,第七部,第十九章,第二十章,第八部,第二十二章,第二十三章,尾 声,备注,","size":"1.20M","feelnum":"0"},{"id":"77","sid":"127","b_name":"梵高传","b_author":"欧文 斯通","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"名人传记","url":"http://47.104.171.87/content/20190103//gaochuan.txt","catalog":[],"size":"0.86M","feelnum":"0"},{"id":"78","sid":"128","b_name":"林肯传","b_author":"卡耐基","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"名人传记","url":"http://47.104.171.87/content/20190103//linkenchuan.txt","catalog":[],"size":"0.43M","feelnum":"0"},{"id":"79","sid":"129","b_name":"马克思传","b_author":"萧灼基","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"名人传记","url":"http://47.104.171.87/content/20190103//makesichuan.txt","catalog":[],"size":"0.26M","feelnum":"0"},{"id":"80","sid":"130","b_name":"过去2000年最伟大的发明","b_author":"约翰 布罗克曼","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"科学技术","url":"http://47.104.171.87/content/20190103//guoqu2000nianzuiweidadefaming.pdf","catalog":[],"size":"3.49M","feelnum":"0"},{"id":"82","sid":"132","b_name":"时间简史","b_author":"史蒂芬 霍金","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"科学技术","url":"http://47.104.171.87/content/20190103//shijianjianshi.txt","catalog":[],"size":"0.20M","feelnum":"0"},{"id":"83","sid":"133","b_name":"科学与艺术","b_author":"李政道 柳怀祖","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"科学技术","url":"http://47.104.171.87/content/20190103//kexueyuyishu.pdf","catalog":[],"size":"6.57M","feelnum":"0"},{"id":"84","sid":"134","b_name":"圣经的故事","b_author":"亨德里克 威廉 房龙","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//shengjinggushi.txt","catalog":[],"size":"0.78M","feelnum":"0"},{"id":"85","sid":"135","b_name":"美学散步","b_author":"宗白华","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//meixuesanbu.txt","catalog":[],"size":"0.23M","feelnum":"0"},{"id":"86","sid":"136","b_name":"孙子兵法","b_author":"孙武","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//sunzibingfa.txt","catalog":[],"size":"0.21M","feelnum":"0"},{"id":"87","sid":"137","b_name":"美的历程","b_author":"李泽厚","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//meidelicheng.pdf","catalog":[],"size":"38.84M","feelnum":"0"},{"id":"88","sid":"138","b_name":"三十六计","b_author":"佚名","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//sanshiliuji.txt","catalog":[],"size":"0.14M","feelnum":"0"},{"id":"89","sid":"139","b_name":"苏菲的世界","b_author":"乔斯坦 贾德","b_lead":"","b_cover":null,"b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//sufeideshijie.txt","catalog":[],"size":"0.61M","feelnum":"0"},{"id":"90","sid":"140","b_name":"中国绘画史","b_author":"王伯敏","b_lead":"","b_cover":"0","b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//zhongguohuihuashi.epub","catalog":"第二章 汉代之绘画,第三章 六朝之绘画,第四章 魏晋之绘画,第五章 南北朝之绘画,第六章 隋朝之绘画,第二章 五代之绘画,第三章 宋朝之绘画,第四章 元朝之绘画,第二章 清朝之绘画,清代花卉之派别,文人画之价值,中国人物画之变迁,绘画源于实用说,","size":"6.69M","feelnum":"0"},{"id":"91","sid":"141","b_name":"非暴力沟通","b_author":"马歇尔 卢森堡","b_lead":"","b_cover":"0","b_introduction":"","readnum":"0","typename":"人生修养","url":"http://47.104.171.87/content/20190103//feibaoligoutong.epub","catalog":"总目录,非暴力沟通,译序,前言,语言是窗户（否则，它们是墙）,第一章 让爱融入生活,第二章 是什么蒙蔽了爱？,第三章 区分观察和评论,第四章 体会和表达感受,第五章 感受的根源,第六章 请求帮助,第七章 用全身心倾听,第八章 倾听的力量,第九章 爱自己,第十章 充分表达愤怒,第十一章 运用强制力避免伤害,第十二章 重获生活的热情,第十三章 表达感激,后记,附录：非暴力沟通模式,编后记,非暴力沟通亲子篇,序,前　言,简　介,SectionⅠ　尊重和合作的基础,Chapter1　尊重和合作：家长渴望什么以及如何获得？,··合作必须是双向的··,··合作是一种生存技能··,··合作\u2014\u2014可持续发展所需要的技能··,··合作，是跟你的孩子一起协作··,··尊重，是一种\u201c看事情的方式\u201d··,··合作，根植在我们的基因里··,Chapter 2　自我尊重：家长也有需要,··你的需要很重要··,··自我尊重很重要··,Chapter 3　是什么让合作难以为继？,··有限的联结时间··,··标签、攀比和挑剔··,··奖励和惩罚··,··思考和沟通的习惯··,··小　结··,SectionⅡ　开启合作的七把钥匙,第一把钥匙：做目标明确的家长,··确定你的育儿目标··,··用你的目标校准你的思考··,··让你的行动符合你的目标··,··让你的沟通支持你的目标··,··鼓励孩子做出自己的选择··,··小　结··,第二把钥匙：看到行为背后的需要,··所有的行为都在试图满足某种需要··,··孩子们总是在努力地满足自己的需要··,··你要为自己的需要负责··,··感受是需要的信使··,··孩子需要倾听和理解··,··小　结··,第三把钥匙　建立安全感、信任感和归属感,··孩子的成长需要情感上的安全感··,··你的行为影响孩子的情感安全感··,··学会从孩子的视角看问题··,··自始至终寻求联结，来维护安全感··,··培育家庭成员之间的联结，来加强安全感、信任感和归属感··,··小　结··,第四把钥匙　激励给予,··给予是人类的基本需要··,··你和孩子们有很多礼物可以相互给予··,··接受孩子的礼物··,··慷慨地给予你的礼物··,··从孩子鲜活的礼物中学习··,··小　结··,第五把钥匙　使用尊重的语言,··记住你的意愿··,··留意沟通中的\u201c流\u201d··,··给出没有评判的观察··,··联结感受和需要··,··提出\u201c可执行的\u201d请求··,··全身心地倾听··,··长颈鹿式的自我倾听··,··总　结··,第六把钥匙　在成长中学习,··无论发生了什么，你都能处理··,··你和孩子可以合作，一起做出决定，一起解决问题··,··有很多满足需要的方式··,··有效的方法值得庆祝··,··从无效的做法中总结经验··,··总　结··,第七把钥匙　让你的家成为\u201c无错区\u201d（No-Fault Zone）,··选择将冲突视为要解决的问题··,··确信你的需要可以得到满足··,··相信需要可以引出解决方案··,··用合作化解冲突··,··把战区变为\u201c无错区\u201d··,··小　结··,SectionⅢ　家庭活动以及来自\u201c无错区\u201d的故事分享,主题：长颈鹿文化与豺狗文化,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：家庭会议,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：丰盈生命之体验,主题：平和地化解冲突,主题：平和地化解冲突,主题：平和地化解冲突,主题：平和地化解冲突,主题：平和地化解冲突,主题：长颈鹿与豺狗游戏,主题：长颈鹿与豺狗游戏,来自\u201c无错区\u201d的故事分享,··自我倾听解救了我··,··全然接纳的力量··,··用倾听建立联结··,··转换vs妥协··,··看到事情的两面··,··一起找到解决方案··,··久违了的轻松时刻··,··爱哭闹的孩子们··,··从混乱到安静··,··自愿帮助··,··这个不是我想教给他的··,··对未来的希望··,··处理vs共情··,··我真的想让他听到这些··,关于作者,","size":"3.41M","feelnum":"0"}]
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
         * id : 81
         * sid : 131
         * b_name : 科学改变人类生活的100个瞬间
         * b_author : 路甬祥
         * b_lead :
         * b_cover : null
         * b_introduction :
         * readnum : 0
         * typename : 科学技术
         * url : http://47.104.171.87/content/20190103//kexuegaibianrenleishenghuode100geshunjian.txt
         * catalog : []
         * size : 0.01M
         * feelnum : 0
         */

        private String id;
        private String sid;
        private String b_name;
        private String b_author;
        private String b_lead;
        private Object b_cover;
        private String b_introduction;
        private String readnum;
        private String typename;
        private String url;
        private String size;
        private String feelnum;
        private List<?> catalog;

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

        public Object getB_cover() {
            return b_cover;
        }

        public void setB_cover(Object b_cover) {
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

        public List<?> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<?> catalog) {
            this.catalog = catalog;
        }
    }
}
