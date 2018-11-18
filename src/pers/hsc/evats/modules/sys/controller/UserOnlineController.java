package pers.hsc.evats.modules.sys.controller;

import pers.hsc.evats.core.common.controller.BaseController;
import pers.hsc.evats.core.query.annotation.PageableDefaults;
import pers.hsc.evats.core.query.data.Page;
import pers.hsc.evats.core.query.data.PageImpl;
import pers.hsc.evats.core.query.data.PropertyPreFilterable;
import pers.hsc.evats.core.query.data.Queryable;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import pers.hsc.evats.core.security.shiro.session.SessionDAO;
import pers.hsc.evats.core.utils.StringUtils;
import pers.hsc.evats.modules.sys.entity.UserOnline;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.hsc.evats.core.model.AjaxJson;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.core.utils.EvatsPropertiesUtil;
import pers.hsc.evats.modules.sys.security.shiro.session.mgt.OnlineSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**   
 * 在线用户
 * 
 * @author hsc
 *
 * Mar 31, 2018
 */
@Controller
@RequestMapping(value = "${admin.url.prefix}/sys/online")
@RequiresPathPermission("sys:online")
public class UserOnlineController extends BaseController{

	@Autowired
	private SessionDAO sessionDAO;

	public UserOnlineController() {
	}


	@RequiresMethodPermissions("list")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
		// 预处理
		Collection<Session> sessionList=sessionDAO.getActiveSessions(false,null,null);
		List<UserOnline> onlineSessionList=new ArrayList<UserOnline>();
		for (Session session:sessionList) {
			UserOnline userOnline=UserOnline.fromOnlineSession((OnlineSession) session);
			if (!StringUtils.isEmpty(userOnline.getUserId())) {
				onlineSessionList.add(UserOnline.fromOnlineSession((OnlineSession) session));
			}
		}
		model.addAttribute("onlineSessionList",onlineSessionList);
		return display("list");
	}
	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
	@PageableDefaults(sort = "id=desc")
	private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
						  HttpServletResponse response) throws IOException {
		// 预处理
		Collection<Session> onlineSessionList=sessionDAO.getActiveSessions(true,null,null);
		Page<Session> onlineSessionPage=new PageImpl<Session>((List<Session>) onlineSessionList);
		String content = JSON.toJSONString(onlineSessionPage);
		StringUtils.printJson(response, content);
	}
	@RequestMapping("/forceLogout")
	@ResponseBody
	public AjaxJson forceLogout(@RequestParam(value = "ids") String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setMsg("强制退出成功");
		if (EvatsPropertiesUtil.getProperties().getBoolean("demoMode")) {
			ajaxJson.fail("演示模式，不允许强制退出用户！");
			return ajaxJson;
		}
		for (String id : ids) {
			OnlineSession onlineSession = (OnlineSession) sessionDAO.readSession(id);
			if (onlineSession == null) {
				continue;
			}
			onlineSession.setStatus(OnlineSession.OnlineStatus.force_logout);
			//sessionDAO.update(onlineSession);
		}
		return ajaxJson;
	}

}
