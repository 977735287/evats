package pers.hsc.evats.modules.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.modules.user.entity.RfidTag;

/**
 * 标签控制器
 * 
 * @author hsc
 *
 *         Apr 9, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/user/tag")
@RequiresPathPermission("user:tag")
public class TagController extends BaseCRUDController<RfidTag, String> {

}
