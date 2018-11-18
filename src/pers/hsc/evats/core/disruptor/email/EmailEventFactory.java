package pers.hsc.evats.core.disruptor.email;

import com.lmax.disruptor.EventFactory;

/**
 * 定义事件工厂
 * 
 * @author hsc
 *
 *         Mar 29, 2018
 */
public class EmailEventFactory implements EventFactory<EmailEvent> {
	public EmailEvent newInstance() {
		return new EmailEvent();
	}
}