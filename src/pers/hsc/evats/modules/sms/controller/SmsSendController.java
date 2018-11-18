package pers.hsc.evats.modules.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.hsc.evats.core.common.controller.BaseController;
import pers.hsc.evats.core.model.AjaxJson;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.core.utils.sms.data.SmsResult;
import pers.hsc.evats.modules.sms.service.ISmsSendService;

/**
 * 短信发送
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/sms/send")
@RequiresPathPermission("sms:send")
public class SmsSendController extends BaseController {
	@Autowired
	private ISmsSendService smsSendService;

	@RequestMapping(value = "/sms", method = RequestMethod.GET)
	public String sms(HttpServletRequest request, HttpServletResponse response) {
		return display("sms");
	}

	@RequestMapping(value = "/huyiSendSmsByCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson huyiSendSmsByCode(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("短信发送成功");
		try {
			String phone = request.getParameter("phone");
			String code = request.getParameter("code");
			String data = request.getParameter("data");
			SmsResult smsResult = null;
			if (!StringUtils.isEmpty(data)) {
				String[] datas = data.split(",");
				smsResult = smsSendService.huyiSendSmsByCode(phone, code, datas);
			} else {
				smsResult = smsSendService.huyiSendSmsByCode(phone, code);
			}

			if (!smsResult.isSuccess()) {
				ajaxJson.fail(smsResult.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("短信发送失败");
		}
		return ajaxJson;
	}
	
	@RequestMapping(value = "/ucpaasSendSmsByCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson ucpaasSendSmsByCode(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("短信发送成功");
		try {
			String phone = request.getParameter("phone");
			String code = request.getParameter("code");
			String data = request.getParameter("data");
			SmsResult smsResult = null;
			if (!StringUtils.isEmpty(data)) {
				smsResult = smsSendService.ucpaasSendSmsByCode(phone, code, data);
			} else {
				smsResult = smsSendService.ucpaasSendSmsByCode(phone, code);
			}

			if (!smsResult.isSuccess()) {
				ajaxJson.fail(smsResult.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("短信发送失败");
		}
		return ajaxJson;
	}
}