package pers.hsc.evats.core.utils.sms.sender.ucpaas;

import org.apache.log4j.Logger;

/**
 * @author hsc
 *
 * Apr 16, 2018
 */
public class UcpaasSDK {
	protected Logger logger = Logger.getLogger(UcpaasSDK.class);
	
	static AbsRestClient InstantiationRestAPI() {
		return new JsonReqClient();
	}
	
	public static String sendSms(String sid, String token, String appid, String templateid, String param, String mobile, String uid){
		String result = "";
		try {
			result=InstantiationRestAPI().sendSms(sid, token, appid, templateid, param, mobile, uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void sendSmsBatch(String sid, String token, String appid, String templateid, String param, String mobile, String uid){
		try {
			String result=InstantiationRestAPI().sendSmsBatch(sid, token, appid, templateid, param, mobile, uid);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addSmsTemplate(String sid, String token, String appid, String type, String template_name, String autograph, String content){
		try {
			String result=InstantiationRestAPI().addSmsTemplate(sid, token, appid, type, template_name, autograph, content);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	
	public static void getSmsTemplate(String sid, String token, String appid, String templateid, String page_num, String page_size){
		try {
			String result=InstantiationRestAPI().getSmsTemplate(sid, token, appid, templateid, page_num, page_size);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void editSmsTemplate(String sid, String token, String appid, String templateid, String type, String template_name, String autograph, String content){
		try {
			String result=InstantiationRestAPI().editSmsTemplate(sid, token, appid, templateid, type, template_name, autograph, content);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void deleterSmsTemplate(String sid, String token, String appid, String templateid){
		try {
			String result=InstantiationRestAPI().deleterSmsTemplate(sid, token, appid, templateid);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
