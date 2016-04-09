package com.jsonwong.newframework.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jsonwong.newframework.util.JsonUtils;

import junit.framework.TestCase;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wrd66 on 2016/3/12.
 */
public class JsonUtilsTest extends TestCase {


//    static String json = "{\n" +
//
//            "        {\n" +
//            "            \"postid\": \"BK1QVLUM00234KO7\",\n" +
//            "            \"url_3w\": \"http://help.3g.163.com/16/0407/09/BK1QVLUM00234KO7.html\",\n" +
//            "            \"votecount\": 790,\n" +
//            "            \"replyCount\": 1704,\n" +
//            "            \"skipID\": \"S1451880983492\",\n" +
//            "            \"ltitle\": \"习近平对“两学一做”学习教育作出重要指示\",\n" +
//            "            \"digest\": \"开展“两学一做”学习教育，要把做人做事的底线划出来。\",\n" +
//            "            \"skipType\": \"special\",\n" +
//            "            \"url\": \"http://3g.163.com/ntes/16/0407/09/BK1QVLUM00234KO7.html\",\n" +
//            "            \"specialID\": \"S1451880983492\",\n" +
//            "            \"docid\": \"BK1QVLUM00234KO7\",\n" +
//            "            \"title\": \"习近平：基层是党的执政之基\",\n" +
//            "            \"source\": \"人民网\",\n" +
//            "            \"priority\": 240,\n" +
//            "            \"lmodify\": \"2016-04-07 14:14:53\",\n" +
//            "            \"boardid\": \"news_gov_bbs\",\n" +
//            "            \"subtitle\": \"\",\n" +
//            "            \"imgsrc\": \"http://img5.cache.netease.com/3g/2016/4/7/201604071445156835b.jpg\",\n" +
//            "            \"ptime\": \"2016-04-07 09:50:14\"\n" +
//            "        }\n" +
//            "       \n" +
//
//            "}";

    static String json = "\n" +
            "{\"T1348647909107\":[{\"postid\":\"PHOT3GK4000100AP\",\"hasCover\":false,\"hasHead\":1,\"replyCount\":25718,\"hasImg\":1,\"digest\":\"\",\"hasIcon\":false,\"docid\":\"9IG74V5H00963VRO_BK4G712QliushuqiupdateDoc\",\"title\":\"湖北\\\"汉服控\\\"女大学生每天穿汉服上课\",\"order\":1,\"priority\":253,\"lmodify\":\"2016-04-08 10:39:43\",\"boardid\":\"photoview_bbs\",\"ads\":[{\"title\":\"武汉垃圾肉炼油装入食用油罐车\",\"tag\":\"photoset\",\"imgsrc\":\"http://img6.cache.netease.com/3g/2016/4/8/201604081124294107f.jpg\",\"subtitle\":\"\",\"url\":\"00AP0001|115337\"},{\"title\":\"妙龄女恋69岁老汉 失恋后欲跳江轻生\",\"tag\":\"photoset\",\"imgsrc\":\"http://img5.cache.netease.com/3g/2016/4/8/20160408104745686d7.jpg\",\"subtitle\":\"\",\"url\":\"00AP0001|115304\"},{\"title\":\"西安女子被外墙瓷片砸伤头 赔偿陷僵局\",\"tag\":\"photoset\",\"imgsrc\":\"http://img2.cache.netease.com/3g/2016/4/8/2016040807274558e12.jpg\",\"subtitle\":\"\",\"url\":\"00AP0001|115303\"},{\"title\":\"苹果在建新总部Apple Campus 2曝光\",\"tag\":\"photoset\",\"imgsrc\":\"http://img4.cache.netease.com/3g/2016/4/8/201604081008108ea93.jpg\",\"subtitle\":\"\",\"url\":\"00AO0001|115329\"},{\"title\":\"金正恩视察机械厂 号召迎接\\\"七大\\\"\",\"tag\":\"photoset\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/2016040809555601c57.jpg\",\"subtitle\":\"\",\"url\":\"00AO0001|115328\"}],\"photosetID\":\"00AP0001|115332\",\"template\":\"manual\",\"votecount\":22584,\"skipID\":\"00AP0001|115332\",\"alias\":\"Top News\",\"skipType\":\"photoset\",\"cid\":\"C1348646712614\",\"hasAD\":1,\"imgextra\":[{\"imgsrc\":\"http://img5.cache.netease.com/3g/2016/4/8/20160408104313e5341.jpg\"},{\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/201604081040358a0c8.jpg\"}],\"source\":\"中新网\",\"ename\":\"androidnews\",\"tname\":\"头条\",\"imgsrc\":\"http://img6.cache.netease.com/3g/2016/4/8/2016040810425311914.jpg\",\"ptime\":\"2016-04-08 10:39:43\"},{\"postid\":\"BK4DDNII00234KO7\",\"url_3w\":\"http://help.3g.163.com/16/0408/09/BK4DDNII00234KO7.html\",\"votecount\":0,\"replyCount\":62,\"skipID\":\"S1451880983492\",\"ltitle\":\"习近平:全面从严治党首先要尊崇党章\",\"digest\":\"各级纪委要全面履行党章赋予的职责，带头尊崇党章。\",\"skipType\":\"special\",\"url\":\"http://3g.163.com/ntes/16/0408/09/BK4DDNII00234KO7.html\",\"specialID\":\"S1451880983492\",\"docid\":\"BK4DDNII00234KO7\",\"title\":\"习近平：从严治党要尊崇党章\",\"source\":\"新华网$\",\"priority\":240,\"lmodify\":\"2016-04-08 09:50:57\",\"boardid\":\"news_gov_bbs\",\"subtitle\":\"\",\"imgsrc\":\"http://img2.cache.netease.com/3g/2016/4/8/20160408095428b230e.jpg\",\"ptime\":\"2016-04-08 09:50:57\"},{\"postid\":\"BK4BS8GE00963VRO\",\"url_3w\":\"http://help.3g.163.com/16/0408/09/BK4BS8GE00963VRO.html\",\"votecount\":72923,\"replyCount\":80335,\"ltitle\":\"和颐酒店涉案男子在河南被警方抓获\",\"digest\":\"北京警方在河南警方配合下，在许昌抓获24岁嫌犯。\",\"url\":\"http://3g.163.com/ntes/16/0408/09/BK4BS8GE00963VRO.html\",\"docid\":\"BK4BS8GE00963VRO\",\"title\":\"\\\"女孩酒店遇袭\\\"涉案男子被抓\",\"TAGS\":\"视频\",\"source\":\"平安北京\",\"priority\":140,\"lmodify\":\"2016-04-08 10:24:05\",\"boardid\":\"3g_bbs\",\"imgsrc\":\"http://img1.cache.netease.com/3g/2016/4/8/201604080926583ad51.jpg\",\"subtitle\":\"\",\"ptime\":\"2016-04-08 09:23:56\",\"TAG\":\"视频\"},{\"url_3w\":\"http://help.3g.163.com/16/0408/11/BK4I3Q9J00963VRO.html\",\"postid\":\"BK4I3Q9J00963VRO\",\"votecount\":0,\"replyCount\":1294,\"ltitle\":\"上海交大建校120周年 江泽民为母校发贺信\",\"digest\":\"第三次在上海交大校庆之际发贺信，曾十余次莅临母校。\",\"url\":\"http://3g.163.com/ntes/16/0408/11/BK4I3Q9J00963VRO.html\",\"docid\":\"BK4I3Q9J00963VRO\",\"title\":\"江泽民发贺信庆母校建校120年\",\"source\":\"上海交通大学\",\"priority\":120,\"lmodify\":\"2016-04-08 11:35:03\",\"imgsrc\":\"http://img4.cache.netease.com/3g/2016/4/8/20160408112511acbaa.jpg\",\"subtitle\":\"\",\"boardid\":\"3g_bbs\",\"ptime\":\"2016-04-08 11:12:55\"},{\"postid\":\"BK4KO70800963VRO\",\"url_3w\":\"http://help.3g.163.com/16/0408/11/BK4KO70800963VRO.html\",\"votecount\":1436,\"replyCount\":1612,\"ltitle\":\"和颐酒店遇袭女孩回应嫌犯被抓:我的诉求已实现\",\"digest\":\"嫌犯被抓、如家道歉整改，称不再接受采访回归平静生活。\",\"url\":\"http://3g.163.com/ntes/16/0408/11/BK4KO70800963VRO.html\",\"docid\":\"BK4KO70800963VRO\",\"title\":\"和颐酒店遇袭女孩:诉求已实现\",\"TAGS\":\"视频\",\"source\":\"央视\",\"priority\":101,\"lmodify\":\"2016-04-08 12:06:37\",\"boardid\":\"3g_bbs\",\"imgsrc\":\"http://img4.cache.netease.com/3g/2016/4/8/20160408120256f23c7.jpg\",\"subtitle\":\"\",\"ptime\":\"2016-04-08 11:59:01\",\"TAG\":\"视频\"},{\"url_3w\":\"http://news.163.com/16/0408/03/BK3M25IQ0001124J.html\",\"postid\":\"BK3M25IQ0001124J\",\"votecount\":96077,\"replyCount\":101229,\"ltitle\":\"鞍钢巨亏近46亿 员工工资下降自嘲成\\\"啃老族\\\"\",\"digest\":\"\\\"前年找对象非鞍钢不要，现在说鞍钢工资太低不提这茬。\\\"\",\"url\":\"http://3g.163.com/news/16/0408/03/BK3M25IQ0001124J.html\",\"docid\":\"BK3M25IQ0001124J\",\"title\":\"鞍钢亏46亿 员工自嘲\\\"啃老族\\\"\",\"source\":\"新京报\",\"priority\":100,\"lmodify\":\"2016-04-08 07:31:32\",\"imgsrc\":\"http://img2.cache.netease.com/3g/2016/4/8/2016040803173435b84.jpg\",\"subtitle\":\"\",\"boardid\":\"news_guonei8_bbs\",\"ptime\":\"2016-04-08 03:02:41\"},{\"postid\":\"BK4HTLPL00964LQ9\",\"url_3w\":\"http://help.3g.163.com/16/0408/11/BK4HTLPL00964LQ9.html\",\"votecount\":563,\"replyCount\":861,\"ltitle\":\"每日轻松一刻:脑好编代码，胸大走天下\",\"digest\":\"智商再高有啥用？最终还不是撸倒在电脑前！\",\"url\":\"http://3g.163.com/ntes/16/0408/11/BK4HTLPL00964LQ9.html\",\"docid\":\"BK4HTLPL00964LQ9\",\"title\":\"每日轻松一刻:好脑斗不过大胸\",\"editor\":[],\"TAGS\":\"独家\",\"source\":\"轻松一刻工作室\",\"imgType\":1,\"priority\":94,\"lmodify\":\"2016-04-08 12:09:11\",\"boardid\":\"3g_bbs\",\"subtitle\":\"\",\"imgsrc\":\"http://img4.cache.netease.com/3g/2016/4/8/2016040812032541210.jpg\",\"ptime\":\"2016-04-08 11:09:34\",\"TAG\":\"独家\"},{\"postid\":\"PHOT2P32009654GI\",\"votecount\":12292,\"replyCount\":13509,\"skipID\":\"54GI0096|91234\",\"digest\":\"\",\"skipType\":\"photoset\",\"docid\":\"9IG74V5H00963VRO_BK3QQTAQwangguanchenupdateDoc\",\"title\":\"富豪子女学英式礼仪1天3800元\",\"source\":\"中国青年网\",\"imgextra\":[{\"imgsrc\":\"http://img2.cache.netease.com/3g/2016/4/8/20160408042707eed6f.jpg\"},{\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/2016040804271094066.jpg\"}],\"priority\":94,\"lmodify\":\"2016-04-08 04:26:06\",\"boardid\":\"photoview_bbs\",\"imgsrc\":\"http://img2.cache.netease.com/3g/2016/4/8/201604080427052649e.jpg\",\"photosetID\":\"54GI0096|91234\",\"ptime\":\"2016-04-08 04:26:06\"},{\"url_3w\":\"http://help.3g.163.com/16/0408/12/BK4M22S100963VRO.html\",\"postid\":\"BK4M22S100963VRO\",\"votecount\":0,\"replyCount\":5,\"ltitle\":\"济广高速客车侧翻已致5人死亡\",\"digest\":\"客车出发1小时出事，核载55人数人受伤，人员已疏散。\",\"url\":\"http://3g.163.com/ntes/16/0408/12/BK4M22S100963VRO.html\",\"docid\":\"BK4M22S100963VRO\",\"title\":\"济广高速客车侧翻已致5人死亡\",\"source\":\"央视\",\"priority\":92,\"lmodify\":\"2016-04-08 12:25:45\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/20160408122215de7f8.jpg\",\"subtitle\":\"\",\"boardid\":\"3g_bbs\",\"ptime\":\"2016-04-08 12:21:53\"},{\"url_3w\":\"http://help.3g.163.com/16/0408/09/BK4BEDMH00963VRO.html\",\"postid\":\"BK4BEDMH00963VRO\",\"votecount\":8922,\"replyCount\":9800,\"ltitle\":\"网传福建漳浦女警酒后用高跟鞋狂殴保安大叔\",\"digest\":\"被指酒后与小区保安大叔争执，用高跟鞋将保安大叔打伤。\",\"url\":\"http://3g.163.com/ntes/16/0408/09/BK4BEDMH00963VRO.html\",\"docid\":\"BK4BEDMH00963VRO\",\"title\":\"曝福建漳浦女警酒后狂殴保安\",\"source\":\"澎湃新闻网\",\"priority\":92,\"lmodify\":\"2016-04-08 11:16:06\",\"imgsrc\":\"http://img1.cache.netease.com/3g/2016/4/8/201604080918022d9fe.jpg\",\"subtitle\":\"\",\"boardid\":\"3g_bbs\",\"ptime\":\"2016-04-08 09:16:23\"},{\"url_3w\":\"http://help.3g.163.com/16/0408/09/BK4BCFI000963VRO.html\",\"postid\":\"BK4BCFI000963VRO\",\"votecount\":501,\"replyCount\":608,\"ltitle\":\"如何看待\\\"学生爱看言情小说\\\"？应不应该禁止？\",\"digest\":\"会影响学习吗？年纪小看这些合适吗？欢迎来讨论！\",\"url\":\"http://3g.163.com/ntes/16/0408/09/BK4BCFI000963VRO.html\",\"docid\":\"BK4BCFI000963VRO\",\"title\":\"家长该禁止学生看言情小说吗?\",\"source\":\"网易新闻\",\"priority\":92,\"lmodify\":\"2016-04-08 11:14:00\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2015/10/11/20151011123839c5bb8.jpg\",\"subtitle\":\"\",\"boardid\":\"3g_bbs\",\"ptime\":\"2016-04-08 09:15:19\"},{\"url_3w\":\"http://news.163.com/16/0408/02/BK3KNGMT00011229.html\",\"postid\":\"BK3KNGMT00011229\",\"votecount\":112383,\"replyCount\":118659,\"ltitle\":\"记者暗访酒店招嫖:\\\"鸡头\\\"层层操控卖淫女\",\"digest\":\"卖淫女随叫随到在酒店通行无阻，被\\\"鸡头\\\"严格操控。\",\"url\":\"http://3g.163.com/news/16/0408/02/BK3KNGMT00011229.html\",\"docid\":\"BK3KNGMT00011229\",\"title\":\"记者暗访酒店招嫖:学生妹800\",\"source\":\"新京报\",\"priority\":92,\"lmodify\":\"2016-04-08 07:38:52\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/201604080247365ab19.jpg\",\"subtitle\":\"\",\"boardid\":\"news_shehui7_bbs\",\"ptime\":\"2016-04-08 02:39:24\"},{\"url_3w\":\"http://news.163.com/16/0408/02/BK3JSNUQ00014AED.html\",\"postid\":\"BK3JSNUQ00014AED\",\"votecount\":33470,\"replyCount\":34789,\"ltitle\":\"跨境电商今起告别免税时代 行邮税政策同期调整\",\"digest\":\"有跨境电商承诺自行消化上涨成本，基本保证商品不提价。\",\"url\":\"http://3g.163.com/news/16/0408/02/BK3JSNUQ00014AED.html\",\"docid\":\"BK3JSNUQ00014AED\",\"title\":\"跨境电商今起告别免税时代\",\"source\":\"北青网-北京青年报\",\"priority\":92,\"lmodify\":\"2016-04-08 11:34:31\",\"imgsrc\":\"http://img2.cache.netease.com/3g/2016/4/8/20160408032512c9a18.jpg\",\"subtitle\":\"\",\"boardid\":\"news3_bbs\",\"ptime\":\"2016-04-08 02:24:48\"},{\"url_3w\":\"http://help.3g.163.com/16/0408/01/BK3H202Q00963VRO.html\",\"postid\":\"BK3H202Q00963VRO\",\"votecount\":41238,\"replyCount\":42948,\"ltitle\":\"陕西小镇109人患尘肺病 村民吃2块钱甘草片续命\",\"digest\":\"\\\"尘肺病\\\"村庄100多人患病；医院:患者苦熬失去治疗时机。\",\"url\":\"http://3g.163.com/ntes/16/0408/01/BK3H202Q00963VRO.html\",\"docid\":\"BK3H202Q00963VRO\",\"title\":\"尘肺病村民吃2元钱甘草片续命\",\"source\":\"工人日报\",\"priority\":92,\"lmodify\":\"2016-04-08 08:05:38\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/2016040801401682ce8.jpg\",\"subtitle\":\"\",\"boardid\":\"3g_bbs\",\"ptime\":\"2016-04-08 01:35:13\"},{\"postid\":\"PHOT3GJS000100AP\",\"votecount\":10253,\"replyCount\":10704,\"skipID\":\"00AP0001|115324\",\"digest\":\"\",\"skipType\":\"photoset\",\"docid\":\"9IG74V5H00963VRO_BK4DQ55HbjyuehongdouupdateDoc\",\"title\":\"员工未完成业绩当街下跪爬行\",\"source\":\"网易综合\",\"imgextra\":[{\"imgsrc\":\"http://img6.cache.netease.com/3g/2016/4/8/201604080958339f8f5.jpg\"},{\"imgsrc\":\"http://img5.cache.netease.com/3g/2016/4/8/20160408095835655e6.jpg\"}],\"priority\":90,\"lmodify\":\"2016-04-08 09:57:45\",\"boardid\":\"photoview_bbs\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/20160408095831b3958.jpg\",\"photosetID\":\"00AP0001|115324\",\"ptime\":\"2016-04-08 09:57:45\"},{\"url_3w\":\"http://news.163.com/16/0408/11/BK4IEC0F00011229.html\",\"postid\":\"BK4IEC0F00011229\",\"votecount\":3459,\"replyCount\":3800,\"ltitle\":\"一位高中老师15年后的道歉:打击了她的自信\",\"digest\":\"称女生曾被自己赶出教室，对打击女生的自信表示悔意。\",\"url\":\"http://3g.163.com/news/16/0408/11/BK4IEC0F00011229.html\",\"docid\":\"BK4IEC0F00011229\",\"title\":\"女生高考落榜 老师15年后道歉\",\"source\":\"新华报业网-扬子晚报\",\"priority\":88,\"lmodify\":\"2016-04-08 11:29:10\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/20160408112859faf9f.jpg\",\"subtitle\":\"\",\"boardid\":\"news_shehui7_bbs\",\"ptime\":\"2016-04-08 11:18:41\"},{\"url_3w\":\"\",\"postid\":\"BK2HJ2UC05118O6R\",\"votecount\":694,\"replyCount\":853,\"ltitle\":\"中国网红10年排行榜：第一竟是她\",\"digest\":\"安妮宝贝、芙蓉姐姐、王思聪分别位居网红排名的前三甲。\",\"url\":\"null\",\"docid\":\"BK2HJ2UC05118O6R\",\"title\":\"中国网红10年第一竟是她\",\"source\":\"金箍棒\",\"priority\":80,\"lmodify\":\"2016-04-07 18:20:51\",\"imgsrc\":\"http://img1.cache.netease.com/3g/2016/4/7/20160407163633fcd44.jpg\",\"subtitle\":\"\",\"boardid\":\"dy_wemedia_bbs\",\"ptime\":\"2016-04-07 16:25:18\"},{\"url_3w\":\"http://news.163.com/16/0408/09/BK4DK4SS00014JB6.html\",\"postid\":\"BK4DK4SS00014JB6\",\"votecount\":6554,\"replyCount\":7110,\"ltitle\":\"荆门现三元人民币 全品每张价值约4万元\",\"digest\":\"\\\"三元\\\"面额系借鉴卢布面额体系，大都被回收存世量甚少。\",\"url\":\"http://3g.163.com/news/16/0408/09/BK4DK4SS00014JB6.html\",\"docid\":\"BK4DK4SS00014JB6\",\"title\":\"湖北现三元人民币:每张值4万\",\"source\":\"荆楚网\",\"priority\":88,\"lmodify\":\"2016-04-08 10:19:40\",\"imgsrc\":\"http://img3.cache.netease.com/3g/2016/4/8/201604081010306de49.jpg\",\"subtitle\":\"\",\"boardid\":\"news3_bbs\",\"ptime\":\"2016-04-08 09:54:28\"},{\"url_3w\":\"http://news.163.com/16/0408/09/BK4CJ4650001544E.html\",\"postid\":\"BK4CJ4650001544E\",\"votecount\":2119,\"replyCount\":2273,\"ltitle\":\"转发万次锦鲤也救不了平庸的你，为何还如此疯狂\",\"digest\":\"为什么转发的是锦鲤不是别的？为什么知道没用还转？\",\"url\":\"http://3g.163.com/news/16/0408/09/BK4CJ4650001544E.html\",\"docid\":\"BK4CJ4650001544E\",\"title\":\"转发锦鲤求好运为啥很疯狂？\",\"source\":\"News沸点工作室\",\"priority\":88,\"lmodify\":\"2016-04-08 10:44:13\",\"imgsrc\":\"http://img6.cache.netease.com/cnews/2016/4/8/201604080942291a56f.jpg\",\"subtitle\":\"\",\"boardid\":\"news_guonei8_bbs\",\"ptime\":\"2016-04-08 09:36:26\"},{\"url_3w\":\"http://auto.163.com/16/0408/08/BK49MK0J000859EU.html\",\"postid\":\"BK49MK0J000859EU\",\"votecount\":980,\"replyCount\":1102,\"ltitle\":\"奥迪新技术 显示Kaomoj表情缓解压力\",\"digest\":\"可以显示Kaomoj表情，其主要用来缓解驾驶员的压力。\",\"url\":\"http://3g.163.com/auto/16/0408/08/BK49MK0J000859EU.html\",\"docid\":\"BK49MK0J000859EU\",\"title\":\"灯厂威武 奥迪新大灯可发表情\",\"source\":\"中关村在线\",\"priority\":70,\"lmodify\":\"2016-04-08 09:50:58\",\"imgsrc\":\"http://img5.cache.netease.com/3g/2016/4/8/20160408094831b8c44.jpg\",\"subtitle\":\"\",\"boardid\":\"auto_bbs\",\"ptime\":\"2016-04-08 08:45:54\"},{\"url_3w\":\"http://help.3g.163.com/16/0408/08/BK47547O00963VRO.html\",\"postid\":\"BK47547O00963VRO\",\"votecount\":11442,\"replyCount\":12523,\"ltitle\":\"吓尿！学生表演音乐剧 把剧中的割喉情节当了真\",\"digest\":\"学校剧团演《理发师陶德》，两人在割喉场景中被真割喉。\",\"url\":\"http://3g.163.com/ntes/16/0408/08/BK47547O00963VRO.html\",\"docid\":\"BK47547O00963VRO\",\"title\":\"新西兰两名学生演话剧被割喉\",\"source\":\"英国那些事儿\",\"priority\":88,\"lmodify\":\"2016-04-08 08:04:46\",\"imgsrc\":\"http://img6.cache.netease.com/3g/2016/4/8/201604080802267af98.jpg\",\"subtitle\":\"\",\"boardid\":\"3g_bbs\",\"ptime\":\"2016-04-08 08:01:24\"},{\"postid\":\"PHOT3GJK000100AO\",\"votecount\":2005,\"replyCount\":2210,\"skipID\":\"00AO0001|115316\",\"digest\":\"\",\"skipType\":\"photoset\",\"docid\":\"9IG74V5H00963VRO_BK47N0KHliushuqiupdateDoc\",\"title\":\"澳大利亚狐猴排队吃饭 动作超萌\",\"source\":\"网易原创\",\"imgextra\":[{\"imgsrc\":\"http://img2.cache.netease.com/3g/2016/4/8/2016040808115987ac9.jpg\"},{\"imgsrc\":\"http://img5.cache.netease.com/3g/2016/4/8/20160408081201515bb.jpg\"}],\"priority\":87,\"lmodify\":\"2016-04-08 08:11:10\",\"boardid\":\"photoview_bbs\",\"imgsrc\":\"http://img1.cache.netease.com/3g/2016/4/8/201604080811573cd94.jpg\",\"photosetID\":\"00AO0001|115316\",\"ptime\":\"2016-04-08 08:11:10\"}]}";




    public static void main(String[] args) {
        //int a = 0;
        //Root list = json2ObjectList(Root.class, json);
        // List<Newsbean> list = Newsbean.arrayNewsbeanFromData(json);

        Gson gson = new Gson();

        Map<String, List<NewsListBean>> citys = gson.fromJson(json, new TypeToken<Map<String, ArrayList<NewsListBean>>>() {
        }.getType());


    }

    /**
     * json 2 Object List
     *
     * @param clazz
     * @param json
     * @return
     */
//    public static Root json2ObjectList(Class<Root> clazz, String json) {
//        Moshi moshi = new Moshi.Builder().add(new NewsJsonAdapter()).build();
//        //Map<String,ArrayList<T>>
//        Type listOfCardsType = Types.newParameterizedType(clazz);
//        JsonAdapter<Root> jsonAdapter = moshi.adapter(listOfCardsType);
//
//        try {
//            return jsonAdapter.fromJson(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//    class City {
//
//        int id;
//
//        String name;
//
//        String code;
//
//        String map;
//
//    }
//
//
//    public static void main(String[] args) {
//
//        //列表/array 数据
//
//        String str = "[{'id': '1','code': 'bj','name': '北京','map': '39.90403, 116.40752599999996'}, {'id': '2','code': 'sz','name': '深圳','map': '22.543099, 114.05786799999998'}, {'id': '9','code': 'sh','name': '上海','map': '31.230393,121.473704'}, {'id': '10','code': 'gz','name': '广州','map': '23.129163,113.26443500000005'}]";
//
//        Gson gson = new Gson();
//
//        List<City> rs = new ArrayList<City>();
//
//        Type type = new TypeToken<ArrayList<City>>() {
//        }.getType();
//
//        rs = gson.fromJson(str, type);
//
//        for (City o : rs) {
//
//            System.out.println(o.name);
//
//        }
//
//
//        //map数据
//
//        String jsonStr = "{'1': {'id': '1','code': 'bj','name': '北京','map': '39.90403, 116.40752599999996'},'2': {'id': '2','code': 'sz','name': '深圳','map': '22.543099, 114.05786799999998'},'9': {'id': '9','code': 'sh','name': '上海','map': '31.230393,121.473704'},'10': {'id': '10','code': 'gz','name': '广州','map': '23.129163,113.26443500000005'}}";
//
//        Map<String, City> citys = gson.fromJson(jsonStr, new TypeToken<Map<String, City>>() {
//        }.getType());
//
//        System.out.println(citys.get("1").name + "----------" + citys.get("2").code);
//
//    }


}