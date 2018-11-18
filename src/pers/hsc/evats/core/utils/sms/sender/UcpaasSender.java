package pers.hsc.evats.core.utils.sms.sender;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import pers.hsc.evats.core.utils.PropertiesUtil;
import pers.hsc.evats.core.utils.sms.data.SmsResult;
import pers.hsc.evats.core.utils.sms.data.SmsTemplate;
import pers.hsc.evats.core.utils.sms.sender.ucpaas.UcpaasSDK;

/**
 * @author hsc
 *
 *         Apr 16, 2018
 */
public class UcpaasSender extends SmsSender {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public String sid;
	public String token;
	public String appid;
	public String uid;

	public UcpaasSender() {
		init();
	}

	@Override
	protected void init() {
		PropertiesUtil p = new PropertiesUtil(getConfigname());
		sid = p.getString("sms.account.sid");
		token = p.getString("sms.auth.token");
		appid = p.getString("sms.appid");
		uid = p.getString("sms.uid");
	}

	@Override
	public SmsResult send(String phone, SmsTemplate smsTemplate, String... datas) {
		return null;
	}

	@Override
	public SmsResult sendByHuyi(String phone, SmsTemplate smsTemplate, String... datas) {
		return null;
	}

	@Override
	public SmsResult sendByUcpaas(String phone, SmsTemplate smsTemplate, String... datas) {		
		return sendSms(phone,smsTemplate,datas);
	}


	@SuppressWarnings("unchecked")
	public SmsResult sendSms(String phone, SmsTemplate smsTemplate, String... datas) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String param = "";
		for(String data : datas){
			param += data;
		}
		
		String result = UcpaasSDK.sendSms(sid, token, appid, smsTemplate.getTemplateId(), param, phone, uid);
		logger.info("UcpaasSDK result="+result);
		Gson g = new Gson();
		resultMap = g.fromJson(result, resultMap.getClass());
//		for (String s : resultMap.keySet()) {
//			System.out.println(s + "---" + resultMap.get(s));
//		}
		return mapToResult(resultMap);
	}

	private SmsResult mapToResult(Map<String, Object> result) {
		SmsResult requestResult = new SmsResult();
		requestResult.setSuccess(Boolean.FALSE);
		requestResult.setSenderName(name());
		if (result != null) {
			requestResult.setCode(result.get("code") + "");
			requestResult.setMsg(result.get("msg") + "");
			requestResult.setSmsid(result.get("smsid") + "");
			if ("000000".equals(result.get("code"))) {
				requestResult.setSuccess(Boolean.TRUE);
			}
		}
		return requestResult;
	}

	@Override
	protected String name() {
		return "UCPAAS";
	}

//	public static void main(String[] args) {
		// System.out.println(DateUtils.formatDateTime(new Date()));
//		new UcpaasSender().sendSms("15289599685", "309425", "李某某女士," + DateUtils.formatDateTime(new Date()));
//	}

}
