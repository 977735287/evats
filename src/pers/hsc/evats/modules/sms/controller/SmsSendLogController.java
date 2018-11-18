package pers.hsc.evats.modules.sms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.modules.sms.entity.SmsSendLog;

/**   
 * 短信发送日志
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/sms/smssendlog")
@RequiresPathPermission("sms:smssendlog")
public class SmsSendLogController extends BaseCRUDController<SmsSendLog, String> {

}
