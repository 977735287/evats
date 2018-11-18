package pers.hsc.evats.modules.email.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.modules.email.entity.EmailTemplate;

/**   
 * 邮件发送模板
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/email/emailtemplate")
@RequiresPathPermission("email:emailtemplate")
public class EmailTemplateController extends BaseCRUDController<EmailTemplate, String> {

}
