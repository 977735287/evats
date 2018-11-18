package pers.hsc.evats.core.disruptor.email;

import com.lmax.disruptor.WorkHandler;
import pers.hsc.evats.core.utils.email.Email;
import pers.hsc.evats.core.utils.email.EmailResult;

/**
 * 内容消费者
 * 
 * @author hsc
 *
 *         Mar 29, 2018
 */
public class EmailHandler implements WorkHandler<EmailEvent> {
	private EmailDao emailDao;

	public EmailHandler(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	@Override
	public void onEvent(EmailEvent event) throws Exception {
		EmailResult emailResult = Email.newEmail().send(event.getEmailData().getEmail(),
				event.getEmailData().getSubject(), event.getEmailData().getContent());
		if (event.getHandlerCallBack() != null) {
			event.getHandlerCallBack().onResult(emailResult);
		}

		if (emailDao != null) {
			emailDao.doResult(event.getId(), event.getEmailData(), emailResult);
		}
	}

}