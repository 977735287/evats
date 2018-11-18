package pers.hsc.evats.core.disruptor.sms;

import pers.hsc.evats.core.utils.sms.data.SmsResult;

/**
 * 内容传递
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public class SmsEvent {
	private SmsData smsData;
	private String id;
	private SmsHandlerCallBack handlerCallBack;

	public SmsData getSmsData() {
		return smsData;
	}

	public void setSmsData(SmsData smsData) {
		this.smsData = smsData;
	}

	public SmsHandlerCallBack getHandlerCallBack() {
		return handlerCallBack;
	}

	public void setHandlerCallBack(SmsHandlerCallBack handlerCallBack) {
		this.handlerCallBack = handlerCallBack;
	}

	public interface SmsHandlerCallBack {
		public void onResult(SmsResult smsResult);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}