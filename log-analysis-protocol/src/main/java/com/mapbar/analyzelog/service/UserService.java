package com.mapbar.analyzelog.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import net.sf.json.JSONArray;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.glassfish.grizzly.Grizzly;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.entities.BasicLog;
import com.mapbar.analyzelog.core.entities.BasicLog.ChannelType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogError;
import com.mapbar.analyzelog.core.entities.LogEvent;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.entities.LogTerminate;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;
import com.mapbar.analyzelog.model.Body;
import com.mapbar.analyzelog.model.Ement;
import com.mapbar.analyzelog.model.Error;
import com.mapbar.analyzelog.model.Event;
import com.mapbar.analyzelog.model.Header;
import com.mapbar.analyzelog.model.Launch;
import com.mapbar.analyzelog.model.LogAppUser;
import com.mapbar.analyzelog.model.Prop;
import com.mapbar.analyzelog.model.Terminate;
import com.mapbar.analyzelog.model.UserTodo;
import com.mapbar.analyzelog.util.JsonToBeanUtil;
import com.mapbar.analyzelog.util.StrUtil;
import com.mapbar.analyzelog.util.UrlConnectUtil;

public final class UserService {
	private static final Logger LOGGER = Grizzly.logger(UserService.class);
	private static final LogStorageManager logStorageManager = null;
	private static String damaiURL = "";
	static {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("url");
			damaiURL = bundle.getString("getcity_url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserService() {
	}

	public static LogStorageManager getInstance() {
		return logStorageManager == null ? HBaseLogStorageManager
				.getStorageManager() : logStorageManager;
	}

	/*
	 * public int PutUser(UserTodo todo) { JsonToBeanUtil util=new
	 * JsonToBeanUtil(); util.getJsonObject(todo.getDeviceModel()); Ement
	 * model=(Ement) util.getBean(Ement.class); LogStorageManager service =
	 * UserService.getInstance(); try { LogStorage logStorage =
	 * service.getLogStorage(todo.getApp_id()); String userID =
	 * todo.getUser_id(); Equipment equipment = modelClean(model);
	 * logStorage.putUser(userID, equipment); } catch (Exception e) { return 0;
	 * } return 1; }
	 */

	public final String PutUser(final UserTodo todo) {
		Equipment equipment = new Equipment();

		JsonToBeanUtil util = new JsonToBeanUtil();
		util.getJsonObject(todo.getDeviceModel());
		JSONArray array = util.getoptArray("uid");
		for (int i = 0; i < array.size(); i++) {
			String o = array.get(i).toString();
			if (i == 0)
				equipment.setImei(o);
			else if (i == 1)
				equipment.setMacAddr(o);
		}

		JSONArray array2 = util.getoptArray("pinfo");
		for (int i = 0; i < array2.size(); i++) {
			String o = array2.get(i).toString();
			if (i == 0)
				equipment.setImsi(o);
			else if (i == 1)
				equipment.setMobile(o);
				
		}

		equipment.setOs(util.getStr("os"));
		equipment.setResolution(util.getStr("rsin"));
		equipment.setTimeZone(util.getStr("tm"));
		equipment.setCpu(util.getStr("cpu"));
		String chtype = util.getStr("cht");
		if (chtype != null && !chtype.equals(""))
			equipment.setChannelType(ChannelType.get(Integer.parseInt(chtype)));
		equipment.setLanguage(util.getStr("lang"));
		// country
		equipment.setSdkVersion(util.getStr("sdkv"));
		equipment.setClientTime(util.getStr("time"));
		equipment.setDeviceModel(util.getStr("dm"));
		equipment.setBrand(util.getStr("brand"));
		equipment.setChannelName(util.getStr("chn"));
		String city = "";
		try {
			city = getCity2(util.getStr("lon"), util.getStr("lat"));
		} catch (MalformedURLException e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#PutUser();error:getCity2();message:"+e.getMessage());
		} catch (IOException e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#PutUser();error:getCity2();message:"+e.getMessage());
		}
		equipment.setCity(city);
		equipment.setLat(util.getStr("lat"));
		equipment.setLon(util.getStr("lon"));
		equipment.setAppVersion(util.getStr("appv"));
		equipment.setOSVersion(util.getStr("osv"));
		LogStorageManager service = UserService.getInstance();
		try {
			LogStorage logStorage = service.getLogStorage(todo.getApp_id());
			String userID = todo.getUser_id();
			logStorage.putUser(userID, equipment);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#PutUser();error:hbase.putUser();message:"+e.getMessage());
			return e.getMessage();
		}
		return "";
	}
   /***
    * 连接hbase
    * @description:  方法的描述 
    * @param model
    * @param todo
    * @return
    *
    */
	public final int putLogUserApp(final LogAppUser model,final UserTodo todo) {
		try {
			BasicLog basicLog =setBasicLog(model.getHeadermodel());
			Body body = model.getBodymodel();
			putLuanchs(todo, body.getLaunch(),basicLog);
			putEvents(todo, body.getEvent(),basicLog);
			// putProps(todo,body.getProp());
			putTerminates(todo, body.getTerminates(),basicLog);
			putErrors(todo, body.getErrors(),basicLog);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#putLogUserApp();error:putHbase;message:"+e.getMessage());
			return 0;
		}
		return 1;
	}

	public int putProps(UserTodo todo, List<Prop> modes) {
		/*LogStorageManager service = UserService.getInstance();
		try {
			LogStorage logStorage = service.getLogStorage(todo.getApp_id());
			String userID = todo.getUser_id();
			List<LogTerminate> list = new ArrayList<LogTerminate>();
			for (int i = 0; i < modes.size(); i++) {
				LogTerminate model = getLogProps(basicLog, modes.get(i));
				list.add(model);
			}
			 logStorage.putProps(userID, list);
		} catch (Exception e) {
			return 0;
		}*/
		return 1;
	}

	public final int putTerminates(final UserTodo todo,final List<Terminate> modes,final BasicLog basicLog) {
		LogStorageManager service = UserService.getInstance();
		try {
			LogStorage logStorage = service.getLogStorage(todo.getApp_id());
			String userID = todo.getUser_id();
			List<LogTerminate> list = new ArrayList<LogTerminate>();
			for (int i = 0; i < modes.size(); i++) {
				LogTerminate model = getLogTerminate(basicLog, modes.get(i));
				list.add(model);
			}
			logStorage.putTerminates(userID, list);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#putTerminates();error:putTerminates;message:"+e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public final int putLuanchs(final UserTodo todo,final List<Launch> modes,final BasicLog basicLog) {
		LogStorageManager service = UserService.getInstance();
		try {
			LogStorage logStorage = service.getLogStorage(todo.getApp_id());
			String userID = todo.getUser_id();
			List<LogLaunch> logLuanchs = new ArrayList<LogLaunch>();
			for (int i = 0; i < modes.size(); i++) {
				LogLaunch logLuanch = getLogLaunch(basicLog, modes.get(i));
				logLuanchs.add(logLuanch);
			}
			logStorage.putLuanchs(userID, logLuanchs);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#putLuanchs();error:putLuanchs;message:"+e.getMessage());
			return 0;
		}
		return 1;
	}

	public final int putEvents(final UserTodo todo,final List<Event> modes,final BasicLog basicLog) {
		LogStorageManager service = UserService.getInstance();
		try {
			LogStorage logStorage = service.getLogStorage(todo.getApp_id());
			String userID = todo.getUser_id();
			List<LogEvent> list = new ArrayList<LogEvent>();
			for (int i = 0; i < modes.size(); i++) {
				LogEvent model = getLogEvent(basicLog, modes.get(i));
				list.add(model);
			}
			logStorage.putEvents(userID, list);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#putEvents();error:putEvents;message:"+e.getMessage());
			return 0;
		}
		return 1;
	}

	public final int putErrors(final UserTodo todo,final List<Error> modes,final BasicLog basicLog) {
		LogStorageManager service = UserService.getInstance();
		try {
			LogStorage logStorage = service.getLogStorage(todo.getApp_id());
			String userID = todo.getUser_id();
			List<LogError> list = new ArrayList<LogError>();
			for (int i = 0; i < modes.size(); i++) {
				LogError model = getLogError(basicLog, modes.get(i));
				list.add(model);
			}
			logStorage.putErrors(userID, list);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#putLogUserApp();error:putErrors;message:"+e.getMessage());
			return 0;
		}
		return 1;
	}
	
	/*private String GetCity(String lon, String lat)
			throws MalformedURLException, IOException {
		Map<String, String> paramMap = new HashMap<String, String>();
		String urlData = "";
		if (StrUtil.checkV(lon) && StrUtil.checkV(lat)) {
			paramMap.put("lon", lon);
			paramMap.put("lat", lat);
			try {
				UrlConnectUtil util = new UrlConnectUtil();
				urlData = util.urlConnection(damaiURL, paramMap, "GET",
						"UTF-8", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<String> list1 =new ArrayList();

		
		String xname = "/result/province[@scale='5']";
		String xname2 = "/result/city[@scale='8']";
		XmlParseUtil util = new XmlParseUtil();
		NodeList nodes1 = util.getNodes(urlData, xname);
		NodeList nodes2 = util.getNodes(urlData, xname2);
		List<String> list1 = util.getItem(nodes1);
		list1.addAll(util.getItem(nodes2));
		return list1.toString();
	}*/
    /***
     * 根据坐标取城市
     * @description:  方法的描述 
     * @param lon
     * @param lat
     * @return
     * @throws HttpException
     * @throws IOException
     *
     */
	public final static String getCity2(final String lon,final String lat) throws HttpException, IOException {
		String city=null;
		Map<String, String> paramMap = new HashMap<String, String>();
		String url = "";
		if (StrUtil.checkV(lon) && StrUtil.checkV(lat)) {
			paramMap.put("lon", lon);
			paramMap.put("lat", lat);
			try {
				UrlConnectUtil util = new UrlConnectUtil();
				url = util.urlConnection(damaiURL, paramMap, "GET", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		client.setConnectionTimeout(3000);
		client.setTimeout(3000);
		int result = client.executeMethod(method);
		if (result == 200 && method.getResponseBody().length > 0) {
			String response = new String(method.getResponseBody(), "UTF-8");
			if (response.indexOf("直辖市")==-1) {
				city = response.split("province")[1].split(">|<")[1];
			} else if (response.indexOf("city") > 0){
				city = response.split("city")[1].split(">|<")[1];
			}
		} else {
			city = null;
		}}
		return city;
	}

	private final LogLaunch getLogLaunch(final BasicLog basicLog,final Launch modes) {
		LogLaunch model = new LogLaunch(basicLog);
		model.setClientTime(String.valueOf(modes.getT()));
		model.setSid(modes.getSid());
		return model;
	}

	private final BasicLog setBasicLog(final Header header) {
		BasicLog basicLog = new BasicLog();
		basicLog.setAccess(header.getAccess());
		basicLog.setCarrier(header.getCarrier());
		basicLog.setAppVersion(header.getAppv());
		
		String city = "";
		try {
			city = getCity2(header.getLon(),header.getLat());
		} catch (MalformedURLException e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#PutUser();error:getCity2();message:"+e.getMessage());
		} catch (IOException e) {
			LOGGER.severe("see:com.mapbar.analyzelog.service.UserService#PutUser();error:getCity2();message:"+e.getMessage());
		}
		basicLog.setCity(city);
		String chtype = header.getCht();
		if (chtype != null && !chtype.equals(""))
			basicLog.setChannelType(ChannelType.get(Integer.parseInt(chtype)));
		basicLog.setChannelName(header.getChn());
		basicLog.setLat(header.getLat());
		basicLog.setLon(header.getLon());
		basicLog.setOSVersion(header.getOsv());
		return basicLog;
	}

	private final LogTerminate getLogTerminate(final BasicLog basicLog,final Terminate modes) {
		LogTerminate model = new LogTerminate(basicLog);
		List<String> atvs = modes.getAtvs();
		List<String[]> str = new ArrayList<String[]>();
		if (atvs != null)
			for (int i = 0; i < atvs.size(); i++) {
				Object obj = atvs.get(i);
				List s = (List) obj;
				String[] ss = new String[2];
				for (int j = 0; j < s.size(); j++) {
					Object o = s.get(j);
					if (o != null)
						ss[j] = String.valueOf(o);
				}
				str.add(ss);
			}
		model.setActivities(str);
		model.setDuration(String.valueOf(modes.getDt()));
		model.setSid(modes.getSid());
		model.setClientTime(String.valueOf(modes.getT()));
		return model;
	}

	private LogTerminate getLogProps(BasicLog basicLog, Prop modes) {
		LogTerminate model = new LogTerminate(basicLog);
		model.setDuration("");
		model.setSid("");
		model.setClientTime("");
		return model;
	}

	private final LogEvent getLogEvent(final BasicLog basicLog,final Event modes) {
		LogEvent model = new LogEvent(basicLog);
		model.setAcc(modes.getAcc());
		model.setSid(String.valueOf(modes.getSid()));
		model.setClientTime(String.valueOf(modes.getT()));
		model.setEventID(modes.getEid());
		model.setLable(modes.getLab());
		return model;
	}

	private final LogError getLogError(final BasicLog basicLog,final Error modes) {
		LogError model = new LogError(basicLog);
		model.setClientTime(modes.getT());
		model.setContent(modes.getCt());
		return model;
	}

	private Equipment modelClean(Ement todo) {
		Equipment equipment = new Equipment();
		equipment.setCpu(todo.getCpu());
		equipment.setOs(todo.getOs());
		equipment.setDeviceModel(todo.getDeviceModel());
		equipment.setSdkVersion(todo.getSdkVersion());
		equipment.setResolution(todo.getResolution());
		equipment.setTimeZone(todo.getTimeZone());
		equipment.setLanguage(todo.getLanguage());
		equipment.setSdkVersion(todo.getSdkVersion());
		equipment.setMobile(todo.getMobile());
		return equipment;
	}
}
