package com.mapbar.analyzelog.report.reptail;
/***
 * 解析爬虫爬出来的结果总数
 * @（#）:ReptailParser.java 
 * @description:  
 * @author:  Administrator  2012-4-23 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class ReptailParser {

	public String hiapk(){
		//安卓市场#$m_star m_4$2088042$&$ 
		HtmlParser.extractKeyWordText("http://apk.hiapk.com/SoftDetails.aspx?action=GetBaseInfo&apkId=498645&callback=?","m_4$","hiapk");
		String result = HtmlParser.total;
		String hiapk = result.substring(17,result.indexOf("&")-1);
		System.out.println(hiapk);
		return hiapk;
	}
	public String goapk(){
		//安智#下载：1660961次
		HtmlParser.extractKeyWordText("http://www.anzhi.com/soft_180197.html","下载：","goapk");//安智论坛
		String result = HtmlParser.total;
		String[] st = result.split("：");
	    String goapk = st[1].split("次")[0];
	    System.out.println(goapk);
	    return goapk;
	}
	   public String gfan(){
    	//机锋#1212121
    	 HtmlParser.extractKeyWordText("http://apk.gfan.com/Product/DataDeal.aspx?act=dnum&d=Thu%20Apr%2019%202012%2013%3A29%3A15%20GMT+0800%20%28%u4E2D%u56FD%u6807%u51C6%u65F6%u95F4%29&pid=32320", "","gfan");
    	 String result = HtmlParser.total;
    	 System.out.println(result);
    	 return result;
    }
	    public String nineOne(){
    	//91# 下载次数：383377
    	HtmlParser.extractKeyWordText("http://mobile.91.com/Soft/Android/com.mapbar.android.mapbarmap-V5.6.110873.html", "下载次数：","91");
    	String result = HtmlParser.total;
    	String[] st = result.split("：");
        String nineOne=st[1];
        System.out.println(nineOne);
        return nineOne;
    }
	     public String appchina(){
    	//下载：1109063 次
    	 HtmlParser.extractKeyWordText("http://www.appchina.com/soft_detail_244392_0_10.html", "下载：","appchina");
    	 String result = HtmlParser.total;
    	 String[] st = result.split("：");
	    String apchina = st[1].split("次")[0];
	    return apchina;
    }
	    public String eoe(){
    	//eoe#下载：206542次
    	HtmlParser.extractKeyWordText("http://www.eoemarket.com/apps/18091", "下载：","eoe");
    	String result = HtmlParser.total;
    	String[] st = result.split("：");
	    String eoe = st[1].split("次")[0];
	    System.out.println(eoe);
	    return eoe;
    }
	//cool
//	extractKeyWordText("http://www.coolmart.net.cn/developer/coolmart/ResDetailAction.action?resid=22021", "下载次数：","HD001");
	  public String cool(){
		  HtmlParser.extractKeyWordText("http://www.coolmart.net.cn/developer/coolmart/ResDetailAction.action?resid=22021", "下载次数：","HD001");
	    	String result = HtmlParser.total;
	    	String[] st = result.split("：");
		    String cool = st[1].split("次")[0];
		    System.out.println(cool);
		    return cool;
	  }
	 public String soho(){
		//搜狐#472487
		HtmlParser.extractKeyWordText("http://download.sohu.com/down_detail/0/2641_index.html", "总计","soho");
		String result =  HtmlParser.total;
		System.out.println(result);
		return result;
	}
	 public String mumayi(){
		 //330322
		 HtmlParser.extractKeyWordText("http://www.mumayi.com/plus/disdls.php?aid=24940", "","MUMAYI");
		 String result =  HtmlParser.total;
		 return result;
	 }
	public static void main(String[] args) {
		ReptailParser rp = new ReptailParser();
		System.out.println(rp.gfan());
 	}
}
