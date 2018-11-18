package pers.hsc.evats.core.disruptor.email;

import pers.hsc.evats.core.utils.email.EmailResult;

/**
 * 内容传递
 *
 * @author hsc
 *
 *         Mar 29, 2018
 */
public class EmailEvent {
	private EmailData emailData;
	private String id;
	private EmailHandlerCallBack handlerCallBack;

	public EmailHandlerCallBack getHandlerCallBack() {
		return handlerCallBack;
	}

	public void setHandlerCallBack(EmailHandlerCallBack handlerCallBack) {
		this.handlerCallBack = handlerCallBack;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmailData getEmailData() {
		return emailData;
	}

	public void setEmailData(EmailData emailData) {
		this.emailData = emailData;
	}

	public interface EmailHandlerCallBack {
		public void onResult(EmailResult emailResult);
	}

}