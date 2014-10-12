package com.mapbar.analyzelog.report.reptail;

import java.sql.SQLException;


public class App1000Grab extends Grab {

	public void grabStart(){
		separate("http://apk.hiapk.com/SoftDetails.aspx?action=GetBaseInfo&apkId=498645&callback=?","m_4$","hiapk");
		separate("http://www.anzhi.com/soft_180197.html","下载：","goapk");
		separate("http://apk.gfan.com/Product/DataDeal.aspx?act=dnum&d=Thu%20Apr%2019%202012%2013%3A29%3A15%20GMT+0800%20%28%u4E2D%u56FD%u6807%u51C6%u65F6%u95F4%29&pid=32320", "","gfan");
		separate("http://mobile.91.com/Soft/Android/com.mapbar.android.mapbarmap-V5.6.110873.html", "下载次数：","91");
		separate("http://www.appchina.com/soft_detail_244392_0_10.html", "下载次数","appchina");
		separate("http://www.eoemarket.com/apps/18091", "下载：","eoe");
		separate("http://www.coolmart.net.cn/developer/coolmart/ResDetailAction.action?resid=22021", "下载次数：","HD001");
		separate("http://www.mumayi.com/plus/disdls.php?aid=24940", "","mmy");
		separate("http://download.sohu.com/down_detail/0/2641_index.html", "总计","soho");
	}
	
	public void  separate(String url,String segmentation,String channel){
		try {
		HtmlParser.extractKeyWordText(url,segmentation,channel);
		String result = HtmlParser.total;
		String value = this.getAparmProcess().matchChanel(result.trim(), channel);
		System.out.println("value:"+value);
		this.getJdbcFunction().saveReptile(channel, value.trim(), "1000");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		App1000Grab ag = new App1000Grab();
		ag.grabStart();
	}
}
