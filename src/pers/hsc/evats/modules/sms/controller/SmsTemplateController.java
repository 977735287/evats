package pers.hsc.evats.modules.sms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.modules.sms.entity.SmsTemplate;

/**   
 * 短信模版
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/sms/smstemplate")
@RequiresPathPermission("sms:smstemplate")
public class SmsTemplateController extends BaseCRUDController<SmsTemplate, String> {

}
