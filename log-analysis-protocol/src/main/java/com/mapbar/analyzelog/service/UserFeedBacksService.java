package com.mapbar.analyzelog.service;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.glassfish.grizzly.Grizzly;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;
import com.mapbar.analyzelog.model.FeedBack;
import com.mapbar.analyzelog.model.UserTodo;
import com.mapbar.analyzelog.util.JsonToBeanUtil;

public final class UserFeedBacksService {
	private static final Logger LOGGER = Grizzly.logger(UserFeedBacksService.class);
	private static final LogStorageManager logStorageManager = null;

	public UserFeedBacksService() {
	}

	public static LogStorageManager getInstance() {
		return logStorageManager == null ? HBaseLogStorageManager
				.getStorageManager() : logStorageManager;
	}
   /***
    * 保存反馈
    * @description:  方法的描述 
    * @param model
    * @param todo
    * @return
    *
    */
	public final  String putFeedBacks(final UserTodo todo) {
		String uuid=null;Equipment model=null;
			
			FeedBack vo=modelClean(todo.getDeviceModel(),model);
			vo.setAppid(todo.getApp_id());
			vo.setUserid(todo.getUser_id());
			
			ConfigAppService dao=new ConfigAppService();
		try{
			uuid=dao.setFeedBacks(vo);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserFeedBacksService#putFeedBacks();error:putFeedBacks;message:"+e.getMessage());
			e.printStackTrace();
			return "";
		}
		return uuid;
	}
	
	public final FeedBack getFeedBack(String appid,String userid){
		FeedBack model=null;
		try {
		ConfigAppService service=new ConfigAppService();
		model=service.getFeedBacks(appid, userid);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserFeedBacksService#getFeedBack();error:getFeedBack;message:"+e.getMessage());
			e.printStackTrace();
		}
		return model;
	}
	
	public final List<FeedBack> getFeedBackByQid(String appid,String userid,String qid){
		List<FeedBack> model=null;
		try {
		ConfigAppService service=new ConfigAppService();
		model=service.getFeedBackByQid(appid, userid, qid);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserFeedBacksService#getFeedBack();error:getFeedBack;message:"+e.getMessage());
			e.printStackTrace();
		}
		return model;
	}
	
	public final List<FeedBack> getFeedBackAll(String appid,String userid){
		List<FeedBack> model=null;
		try {
		ConfigAppService service=new ConfigAppService();
		model=service.getFeedBacksAll(appid, userid);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserFeedBacksService#getFeedBack();error:getFeedBack;message:"+e.getMessage());
			e.printStackTrace();
		}
		return model;
	}
	
	public final String getContent(String appid,String userid){
		String rp=null;
		FeedBack model=getFeedBack(appid,userid);
		if(model==null)return "";
		else rp="1";
		return 
		"{"+'"'+"lct"+'"'+":"+'"'+model.getLasttime()+'"'+","+
		'"'+"rp"+'"'+":"+'"'+rp+'"'+","+
		'"'+"ops"+'"'+":{"+'"'+"qid"+'"'+":"+'"'+model.getQid()+'"'+","+
		'"'+"ct"+'"'+":"+'"'+model.getCt()+'"'+
		"}}";
	}
	
	public final String getContentByQid(String appid,String userid,String qid){
		StringBuffer rp=new StringBuffer();
		List<FeedBack> list=getFeedBackByQid(appid, userid, qid);
		if(list==null)return "";
		else 
			for(int i=0;i<list.size();i++){
				FeedBack model=list.get(i);
				if(i==0){
				rp.append("{"+
		'"'+"qid"+'"'+":"+'"'+model.getQid()+'"'+","+
		'"'+"answer"+'"'+":"+"[");
				}
		
		rp.append("{"+'"'+"h"+'"'+":"+'"'+model.getCt()+'"'+","+
		'"'+"htime"+'"'+":"+'"'+model.getAddtime()+'"'+"}");
		
		if(i==list.size()-1)rp.append("]}");else rp.append(",");
			}
		return rp.toString();
	}
	
	public final byte[] getContentAll(String appid,String userid){
		StringBuffer rp=new StringBuffer();
		List<FeedBack> list=getFeedBackAll(appid,userid);
		if(list==null)return null;
		else{
			rp.append("{body:[");
		for(int i=0;i<list.size();i++){
			FeedBack model=list.get(i);
			rp.append("{"+'"'+"lct"+'"'+":"+'"'+model.getLasttime()+'"'+","+
		'"'+"qid"+'"'+":"+'"'+model.getQid()+'"'+","+
		'"'+"ct"+'"'+":"+'"'+model.getCt()+'"'+
		"}");
			if(i!=list.size()-1)rp.append(",");
		}	rp.append("]}");
		}
		byte[] out=new byte[1024];
		return compressString(rp.toString(),out);
	}
	
	protected static final byte[] compressString(final String data,final byte[] output) {
	    Deflater deflater = new Deflater();
	    deflater.setInput(data.getBytes(Charset.forName("utf-8")));
	    deflater.finish();
	    int i=deflater.deflate(output);
	    return Arrays.copyOfRange(output,0,i);
	}
	
	public String getDecompressStr(String appid,String userid){
		byte[] b=getContentAll( appid, userid);
		return decompressString(b,b.length);
	}
	
	protected static String decompressString(byte[] input, int len) {
	    String result = null;
	    try {
	        Inflater inflater = new Inflater();
	        inflater.setInput(input, 0, len);
	        byte[] output = new byte[1024*3];
	        int resultLength = inflater.inflate(output);
	        inflater.end();
	        result = new String(output, 0, resultLength, Charset.forName("utf-8"));
	    } catch (DataFormatException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return result;
	}

	private final static FeedBack modelClean(final String str,final Equipment todo) {
		JsonToBeanUtil util = new JsonToBeanUtil();
		FeedBack equipment = new FeedBack();
		util.getJsonObject(str);
		equipment.setLon(util.getStr("lon"));
		equipment.setLat(util.getStr("lat"));
		equipment.setNote(util.getStr("note"));
		equipment.setCt(util.getStr("ct"));
		equipment.setBrand(util.getStr("brand"));
		equipment.setSdkVersion(util.getStr("sdkv"));
		equipment.setAppVersion(util.getStr("appv"));
		equipment.setOs(util.getStr("dm"));
		if(todo!=null)
		equipment.setPhone(todo.getMobile());
		return equipment;
	}
}
