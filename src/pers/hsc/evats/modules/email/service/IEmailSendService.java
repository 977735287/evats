package pers.hsc.evats.modules.email.service;

import pers.hsc.evats.core.disruptor.email.EmailEvent.EmailHandlerCallBack;
import pers.hsc.evats.core.utils.email.EmailResult;

/**
 * 邮件模版
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public interface IEmailSendService {
	/**
	 * 
	 * @title: sendAsyncEmailByCode
	 * @description: 通过模版编码发送邮件
	 * @param email
	 * @param code
	 * @param datas
	 * @return
	 */
	public void sendAsyncEmailByCode(String email, String code, String... datas);

	/**
	 * 
	 * @title: sendAsyncEmailByCode
	 * @description: 通过模版编码发送邮件
	 * @param email
	 * @param code
	 * @param datas
	 * @return
	 */
	public void sendAsyncEmailByCode(String email, String code, EmailHandlerCallBack callBack, String... datas);

	/**
	 * 
	 * @title: sendSyncEmailByCode
	 * @description: 通过模版编码发送邮件
	 * @param email
	 * @param code
	 * @param datas
	 * @return
	 */
	public EmailResult sendSyncEmailByCode(String email, String code, String... datas);

	/**
	 * 
	 * @title: sendAsyncEmail
	 * @description: 通过內容异步方式发送邮件
	 * @param email
	 * @param code
	 * @param datas
	 * @return
	 */
	public void sendAsyncEmail(String email, String subject, String content, String... datas);

	/**
	 * 
	 * @title: sendAsyncEmail
	 * @description: 通过內容异步方式发送邮件
	 * @param email
	 * @param code
	 * @param datas
	 * @return
	 */
	public void sendAsyncEmail(String email, String subject, String content, EmailHandlerCallBack callBack,
			String... datas);

	/**
	 * 
	 * @title: sendSyncEmail
	 * @description: 通过內容同步方式发送邮件
	 * @param email
	 * @param code
	 * @param datas
	 * @return
	 */
	public EmailResult sendSyncEmail(String email, String subject, String content, String... datas);

}
