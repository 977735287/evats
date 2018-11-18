package pers.hsc.evats.core.disruptor.sms;

import com.lmax.disruptor.EventFactory;

/**
 * 定义事件工厂
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public class SmsEventFactory implements EventFactory<SmsEvent> {
	public SmsEvent newInstance() {
		return new SmsEvent();
	}
}