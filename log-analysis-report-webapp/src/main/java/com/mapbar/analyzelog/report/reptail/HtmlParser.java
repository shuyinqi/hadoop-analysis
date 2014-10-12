package com.mapbar.analyzelog.report.reptail;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.lexer.Page;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;


public class HtmlParser {
	public static String total="";
	// 循环访问所有节点，输出包含关键字的值节点
		public static void extractKeyWordText(String url, String keyword,String name) {
			try {
	            //生成一个解析器对象，用网页的 url 作为参数
				Parser parser = new Parser(url);
				if("soho".equals(name)){
					parser.setEncoding("GBK");
				}else{
					//设置网页的编码,这里只是请求了一个 utf 编码网页
					parser.setEncoding("UTF-8");
				}
				//迭代所有节点, null 表示不使用 NodeFilter
				NodeList list = parser.parse(null);
	            //从初始的节点列表跌倒所有的节点
			    processNodeList(list, keyword,name);
			} catch (ParserException e) {
				e.printStackTrace();
			}
		}

		private static void processNodeList(NodeList list, String keyword,String name) {
			String result="";
			//迭代开始
			SimpleNodeIterator iterator = list.elements();
			while (iterator.hasMoreNodes()) {
				Node node = (Node) iterator.nextNode();
				//得到该节点的子节点列表
				NodeList childList = node.getChildren();
				//孩子节点为空，说明是值节点
				if (null == childList)
				{
					if("crossmo".equals(name)){
						result = node.toHtml();
					}else{
						//得到值节点的值
						result = node.toPlainTextString();
					}
					//若包含关键字，则简单打印出来文本
					if (result.indexOf(keyword) != -1){
						//这个是专门统计搜狐的
                        if("soho".equals(name)){
                        	total = node.getNextSibling().getNextSibling().toPlainTextString();
                        } else if("HD001".equals(name)||"kaiqi".equals(name)){
                        	total=node.getParent().getParent().toPlainTextString();
                        }else if("mmy".equals(name)){
                        	total=node.getText();
                        }else if("uc".equals(name)){
                        	total =node.getParent().getParent().getChildren().elementAt(3).toPlainTextString();
                        }else if("huawei".equals(name)){
                        	total =node.getParent().getParent().getChildren().elementAt(11).toPlainTextString();
                        }else if("crossmo".equals(name)){
                        	total =result;
                        }else if("appchina".equals(name)){
                        	total=node.getParent().getNextSibling().getNextSibling().toPlainTextString();
                        }
                        else {
                        	total=result.trim();
                        }				
					}
				} 
				//孩子节点不为空，继续迭代该孩子节点
				else 
				{
					processNodeList(childList, keyword,name);
				}//end else
			}//end wile
		}
		
		public static void main(String[] args) {
			//安卓市场#$m_star m_4$2088042$&$ 
//			extractKeyWordText("http://apk.hiapk.com/SoftDetails.aspx?action=GetBaseInfo&apkId=498645&callback=?","$m_star","hiapk");
			//安智#下载：1660961次
//			extractKeyWordText("http://www.anzhi.com/soft_180197.html","下载：","goapk");//安智论坛
			//机锋#1212121
//			extractKeyWordText("http://apk.gfan.com/Product/DataDeal.aspx?act=dnum&d=Thu%20Apr%2019%202012%2013%3A29%3A15%20GMT+0800%20%28%u4E2D%u56FD%u6807%u51C6%u65F6%u95F4%29&pid=32320", "","gfan");
			//91# 下载次数：383377
//			extractKeyWordText("http://mobile.91.com/Soft/Android/com.mapbar.android.mapbarmap-V5.6.110873.html", "下载次数：","91");
		    //应用汇#软件大小：5.31M 下载次数：1039366次
			//下载次数 1602065次
//			extractKeyWordText("http://www.appchina.com/soft_detail_244392_0_10.html", "下载次数","appchina");
			//eoe#下载：206542次
//		    extractKeyWordText("http://www.eoemarket.com/apps/18091", "下载：","eoe");
			//cool#1575889
//			extractKeyWordText("http://www.mumayi.com/plus/disdls.php?aid=24940", "","mmy");
			//搜狐#472487
//			extractKeyWordText("http://download.sohu.com/down_detail/0/2641_index.html", "总计","soho");
			//安卓市场随行?("图吧科技$$图吧科技$m_star $2310$&$这个有延迟。。。。有意思
//			extractKeyWordText("http://apk.hiapk.com/SoftDetails.aspx?action=GetBaseInfo&apkId=781135&callback=?","m_star $","hiapk");
			//91随行：下载次数：5835
//			extractKeyWordText("http://mobile.91.com/Soft/Android/com.mapbar.android.accompany-220-2.2.0.html", "下载次数：","91");
			
			//3G门户：随行：下载次数：352
//			extractKeyWordText("http://www.talkphone.cn/Down/Soft/Detail/44815_0.html", "下载次数：","3G");
			//360手机助手随行：下载次数：1476次
//			extractKeyWordText("http://zhushou.360.cn/detail/index/soft_id/152468?recrefer=SE_D_%E9%9A%8F%E8%A1%8C#nogo", "下载次数：","360");
			//应用汇：随行：下载次数 352次
//			extractKeyWordText("http://www.appchina.com/app/com.mapbar.android.accompany/", "下载次数","appchina");
			//十字猫：随行：<dd> 下载次数：27 </dd>
//			extractKeyWordText("http://soft.crossmo.com/softinfo_132675.html", "中间","crossmo");
			//优亿：随行：下载：380次
//			extractKeyWordText("http://www.eoemarket.com/show/index/appId/103489", "下载：","eoe");
			//机锋市场：随行：1591
//			extractKeyWordText("http://apk.gfan.com/Product/DataDeal.aspx?act=dnum&d=Tue%20Sep%2004%202012%2017%3A04%3A33%20GMT+0800%20%28%u4E2D%u56FD%u6807%u51C6%u65F6%u95F4%29&pid=335417", "","gfan");
			//安智：随行下载：下载：570次
//			extractKeyWordText("http://www.anzhi.com/soft_355670.html","下载：","goapk");//安智论坛
			//华为智慧云：随行：Download: 530 
//			extractKeyWordText("http://app.hicloud.com/app/C68280/11","Download:","huawei");
			//开棋：随行：  大小:8352KB下载:75次   发布:2012-09-04 10:47:35
//			extractKeyWordText("http://www.kaiqi.com/search/search.action?keyword=%E9%9A%8F%E8%A1%8C&type=%25E9%259A%258F%25E8%25A1%258C","图吧随行","kaiqi");
			//木蚂蚁：随行:422 
//			extractKeyWordText("http://www.mumayi.com/plus/disdls.php?aid=195249","","mmy");
			//N多：随行：111次下载
//			extractKeyWordText("http://market.nduoa.com/apk/detail/402792","次下载","nduo");
			//天网:随行:153
			extractKeyWordText("http://www.waptw.com/soft/40932.html","下载次数：","uc");
			//腾讯：随行:下载次数：1,000次
//			extractKeyWordText("http://android.myapp.com/android/appdetail.jsp?appid=585627&actiondetail=0&pageNo=1&clickpos=4&softname=%E9%9A%8F%E8%A1%8C&enginekeywd=%E9%9A%8F%E8%A1%8C&lmid=1022","次","QQ");
			
			
		}

}
