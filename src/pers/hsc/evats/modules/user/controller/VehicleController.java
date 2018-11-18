package pers.hsc.evats.modules.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.model.AjaxJson;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.core.utils.R;
import pers.hsc.evats.core.utils.rfid.RFIDReaderUtil;
import pers.hsc.evats.modules.user.entity.RfidTag;
import pers.hsc.evats.modules.user.entity.Vehicle;
import pers.hsc.evats.modules.user.service.ITagService;

/**
 * 车辆管理控制器
 * 
 * @author hsc
 *
 *         Apr 10, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/user/vehicle")
@RequiresPathPermission("user:vehicle")
public class VehicleController extends BaseCRUDController<Vehicle, String> {
	
	@Autowired
	ITagService tagService;

	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "currentTag", method = RequestMethod.POST)
	public R currentTag(HttpServletRequest request, HttpServletResponse response) {
		R r = new R();
		int flag1 = 0;
		int flag2 = 0;
		if(!RFIDReaderUtil.isConnect()) {
			RFIDReaderUtil.connect();
			RFIDReaderUtil.start();
		}else {
			flag1 = 1;
			if(!RFIDReaderUtil.isStart()) {
				RFIDReaderUtil.start();
			}else {
				flag2 = 1;
			}
		}
		
		Thread t = new Thread();
		RFIDReaderUtil.setEPC_LABEL_NUMBER("");
		RFIDReaderUtil.setDEFAULT_CONTROLLER(true);
		boolean isSleep = true;
		while (RFIDReaderUtil.isDEFAULT_CONTROLLER()) {			
			if (RFIDReaderUtil.getEPC_LABEL_NUMBER() != null && RFIDReaderUtil.getEPC_LABEL_NUMBER().trim() != "") {
//				RFIDReaderUtil.stop();  //如果读取到标签就停止读取
				RFIDReaderUtil.setDEFAULT_CONTROLLER(false);
				t.stop();
				if(tagService.findByTagNum(RFIDReaderUtil.getEPC_LABEL_NUMBER()) != null){
//					RFIDReaderUtil.stop();
					return R.error("该标签已被注册，请更换标签！"); //校验数据库是否已存在标签
				}else{
					r.put("tag", RFIDReaderUtil.getEPC_LABEL_NUMBER());
				}
			}
			if(isSleep) {
				t = new Thread() {
					public void run() {
						try {
							sleep(10000);//10秒之后停止读取
//							RFIDReaderUtil.stop();
							RFIDReaderUtil.setDEFAULT_CONTROLLER(false);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				t.start();
				isSleep = false;
			}
		}
		if(flag1 == 0) {
			RFIDReaderUtil.stop();
			RFIDReaderUtil.disConnect();
		}else if(flag1 == 1){
			if(flag2 == 0) {
				RFIDReaderUtil.stop();
			}
		}
//		RFIDReaderUtil.stop();
		return r;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson create(Model model, @Valid @ModelAttribute("data") Vehicle vehicle, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		RfidTag rfidTag = new RfidTag();
		RfidTag rfidTag1 = new RfidTag();
		rfidTag.setTagNum(vehicle.getSelfTag());
		rfidTag.setTagBratherNum(vehicle.getUserTag());
		rfidTag.setTagDetail("车辆标签");
		rfidTag1.setTagNum(vehicle.getUserTag());
		rfidTag1.setTagBratherNum(vehicle.getSelfTag());
		rfidTag1.setTagDetail("用户标签");
		tagService.insert(rfidTag); //保存标签1
		tagService.insert(rfidTag1);//保存标签2
		return doSave(vehicle, request, response, result);
	}


}
