package pers.hsc.evats.core.disruptor.sms;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.hsc.evats.core.utils.sms.data.SmsResult;

/**
 * 短信dao
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public interface SmsDao {
	/**
	 * 
	 * @title: doStart
	 * @description:初始化回调
	 * @return: void
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void doStart();

	/**
	 * 
	 * @title: doShutdown
	 * @description:关闭
	 * @return: void
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void doShutdown();

	/**
	 * 
	 * @title: doShutdown
	 * @description:发送短信
	 * @return: void
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void doSend(String eventId, SmsData smsData);

	/**
	 * 
	 * @title: doShutdown
	 * @description:响应
	 * @return: void
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void doResult(String eventId, SmsData smsData, SmsResult smsResult);
}