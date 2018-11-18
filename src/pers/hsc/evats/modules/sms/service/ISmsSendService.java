package pers.hsc.evats.modules.sms.service;

import pers.hsc.evats.core.disruptor.sms.SmsEvent.SmsHandlerCallBack;
import pers.hsc.evats.core.utils.sms.data.SmsResult;

/**
 * 短信模版
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public interface ISmsSendService {
	/**
	 * @title: sendAsyncSmsByCode
	 * @description: 通过模版编码发送短信
	 * @param phone
	 * @param code
	 * @param datas
	 * @return
	 */
	public void sendAsyncSmsByCode(String phone, String code, String... datas);

	/**
	 * @title: sendAsyncSmsByCode
	 * @description: 通过模版编码发送短信
	 * @param phone
	 * @param code
	 * @param datas
	 * @return
	 */
	public void sendAsyncSmsByCode(String phone, String code, SmsHandlerCallBack callBack, String... datas);

	/**
	 * @title: sendSyncSmsByCode
	 * @description: 通过模版编码发送短信
	 * @param phone
	 * @param code
	 * @param datas
	 * @return
	 */
	public SmsResult huyiSendSmsByCode(String phone, String code, String... datas);

	/**
	 * @title: sendSyncSmsByCode
	 * @description: 通过模版编码发送短信
	 * @param phone
	 * @param code
	 * @param datas
	 * @return
	 */
	public SmsResult ucpaasSendSmsByCode(String phone, String code, String... datas);

}
