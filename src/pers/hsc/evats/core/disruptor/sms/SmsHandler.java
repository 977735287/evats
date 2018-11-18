package pers.hsc.evats.core.disruptor.sms;

import com.lmax.disruptor.WorkHandler;

import pers.hsc.evats.core.utils.sms.data.SmsResult;
import pers.hsc.evats.core.utils.sms.sender.SmsSender;

/**
 * 内容消费者
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public class SmsHandler implements WorkHandler<SmsEvent> {
	private SmsSender smsSender;
	private SmsDao smsDao;

	public SmsHandler(SmsSender smsSender, SmsDao smsDao) {
		this.smsSender = smsSender;
		this.smsDao = smsDao;
	}

	@Override
	public void onEvent(SmsEvent event) throws Exception {
		SmsResult smsResult = smsSender.send(event.getSmsData().getPhone(), event.getSmsData().getSmsTemplate(),
				event.getSmsData().getDatas());
		if (event.getHandlerCallBack() != null) {
			event.getHandlerCallBack().onResult(smsResult);
		}

		if (smsDao != null) {
			smsDao.doResult(event.getId(), event.getSmsData(), smsResult);
		}
	}

}