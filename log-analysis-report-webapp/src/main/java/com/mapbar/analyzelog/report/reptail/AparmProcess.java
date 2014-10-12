package com.mapbar.analyzelog.report.reptail;

public class AparmProcess {
	
	public String matchChanel(String value,String channel){
		String argment="";
		if("hiapk".equals(channel)){
			argment=matchAnd(value);
		}else if("goapk".equals(channel)||"eoe".equals(channel)||"HD001".equals(channel)||"360".equals(channel)){
			argment=matchColonOnce(value);
		}else if("gfan".equals(channel)||"soho".equals(channel)||"mmy".equals(channel)||"uc".equals(channel)){
			argment=matchValue(value);
		}else if("91".equals(channel)||"3G".equals(channel)){
			argment=matchColon(value);
		}else if("huawei".equals(channel)){
			argment=matchColonHtml(value);
		}
		else if("appchina".equals(channel)){
			argment=matchOnce(value);
		}else if("crossmo".equals(channel)){
			argment=matchDownLoadOnceEmpty(value);
		}else if("kaiqi".equals(channel)) {
			argment=matchDownOnce(value);
		}else if("nduo".equals(channel)){
			argment=matchOnce(value);
		}else if("QQ".equals(channel)){
			argment=matchCommaOnce(value);
		}
		return argment;
	}
	/***
	 * 特殊字符：$，不能按此区分
	 * 匹配 hiapk $图吧科技$m_star m_4$3891943$&$ 格式
	 */
	private String matchAnd(String value){
	  StringBuffer chart=new StringBuffer();
	  String hiapk = value.substring(1,value.indexOf("&")-1);
	  StringBuffer sb = new StringBuffer(hiapk);
	  StringBuffer ste = sb.reverse();
	  for(int i=0;i<ste.length();i++){
		  char chste = ste.charAt(i);
		  String stst=String.valueOf(chste);
		  if(stst.matches("\\d+")){
			  chart.append(ste.charAt(i));
		  }else{
			  break;
		  }
	  }
	  return chart.reverse().toString();
	}
	
	private String matchColonOnce(String value){
		String[] st = value.split("：");
	    String goapk = st[1].split("次")[0];
	    return goapk;
	}
	private String matchOnce(String value){
		String st = value.split("次")[0];
		return st;
	}
	private String matchColon(String value){
		String st = value.split("：")[1];
	    return st;
	}
	/***
	 * 特殊字符的:，特有字符
	 */
	private String matchColonHtml(String value){
		String st = value.split(":")[1];
	    return st;
	}
	private String matchDownLoadOnce(String value){
		String st = value.split("数")[1].split("次")[0];
		return st;
	}
	private String matchDownLoadOnceEmpty(String value){
		String st = value.split("下载次数：")[1].split(" ")[0];
		return st;
	}
	private String matchDownOnce(String value){
		String st = value.split("下载:")[1].split("次")[0];
		return st;
	}
	private String matchCommaOnce(String value){
		String st = value.replaceAll(",", "");
		String sr = st.split("次")[0];
		return sr;
	}
	private String matchValue(String value){
		return value;
	}
}
