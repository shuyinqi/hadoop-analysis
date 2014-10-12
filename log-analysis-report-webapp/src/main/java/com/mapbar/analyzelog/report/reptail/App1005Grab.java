package com.mapbar.analyzelog.report.reptail;

import java.sql.SQLException;


public class App1005Grab extends Grab {

	public void grabStart(){
		separate("http://apk.hiapk.com/SoftDetails.aspx?action=GetBaseInfo&apkId=781135&callback=?","m_star","hiapk");
		separate("http://mobile.91.com/Soft/Android/com.mapbar.android.accompany-220-2.2.0.html", "下载次数：","91");
		separate("http://www.appchina.com/app/com.mapbar.android.accompany/", "下载次数","appchina");
		separate("http://www.eoemarket.com/show/index/appId/103489", "下载：","eoe");
		separate("http://apk.gfan.com/Product/DataDeal.aspx?act=dnum&d=Tue%20Sep%2004%202012%2017%3A04%3A33%20GMT+0800%20%28%u4E2D%u56FD%u6807%u51C6%u65F6%u95F4%29&pid=335417", "","gfan");
		separate("http://www.anzhi.com/soft_355670.html","下载：","goapk");
		separate("http://www.mumayi.com/plus/disdls.php?aid=195249","","mmy");
		separate("http://www.talkphone.cn/Down/Soft/Detail/44815_0.html", "下载次数：","3G");
		separate("http://zhushou.360.cn/detail/index/soft_id/152468?recrefer=SE_D_%E9%9A%8F%E8%A1%8C#nogo", "下载次数：","360");
		separate("http://soft.crossmo.com/softinfo_132675.html", "中间","crossmo");
		separate("http://app.hicloud.com/app/C68280/11","Download:","huawei");
		separate("http://www.kaiqi.com/search/search.action?keyword=%E9%9A%8F%E8%A1%8C&type=%25E9%259A%258F%25E8%25A1%258C","图吧随行","kaiqi");
		separate("http://market.nduoa.com/apk/detail/402792","次下载","nduo");
		separate("http://www.waptw.com/soft/40932.html","下载次数：","uc");
		separate("http://android.myapp.com/android/appdetail.jsp?appid=585627&actiondetail=0&pageNo=1&clickpos=4&softname=%E9%9A%8F%E8%A1%8C&enginekeywd=%E9%9A%8F%E8%A1%8C&lmid=1022","次","QQ");

	}
	public void  separate(String url,String segmentation,String channel){
		try {
		HtmlParser.extractKeyWordText(url,segmentation,channel);
		String result = HtmlParser.total;
		String value= this.getAparmProcess().matchChanel(result.trim(), channel);
		System.out.println("value:"+value);
			this.getJdbcFunction().saveReptile(channel, value.trim(), "1005");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		App1005Grab ag = new App1005Grab();
		ag.grabStart();
	}
}
