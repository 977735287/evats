package pers.hsc.evats.core.disruptor.email;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.hsc.evats.core.utils.email.EmailResult;

/**
 * 短信dao
 *
 * @author hsc
 *
 *         Mar 29, 2018
 */
public interface EmailDao {
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
	public void doSend(String eventId, EmailData emailData);

	/**
	 * 
	 * @title: doShutdown
	 * @description:响应
	 * @return: void
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void doResult(String eventId, EmailData emailData, EmailResult emailResult);
}