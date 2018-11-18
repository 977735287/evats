package pers.hsc.evats.modules.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.modules.user.entity.LostVehicle;

/**
 * 失窃车辆管理控制器
 * 
 * @author hsc
 *
 * Apr 11, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/user/lost")
@RequiresPathPermission("user:lost")
public class LostVehicleController extends BaseCRUDController<LostVehicle, String> {

}
