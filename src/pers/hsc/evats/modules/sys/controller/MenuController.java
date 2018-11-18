package pers.hsc.evats.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.hsc.evats.core.common.controller.BaseTreeController;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.modules.sys.entity.Menu;

/**
 * @author hsc
 *
 * Mar 31, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/menu")
@RequiresPathPermission("sys:menu")
public class MenuController extends BaseTreeController<Menu, String> {

}
