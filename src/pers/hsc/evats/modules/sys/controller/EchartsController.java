package pers.hsc.evats.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.hsc.evats.core.common.controller.BaseController;
import pers.hsc.evats.core.query.data.Queryable;
import pers.hsc.evats.core.utils.R;
import pers.hsc.evats.modules.user.entity.LostVehicle;
import pers.hsc.evats.modules.user.entity.VehicleRecord;
import pers.hsc.evats.modules.user.service.ILostVehicleService;
import pers.hsc.evats.modules.user.service.IVehicleRecordService;

/**
 * @author hsc
 *
 * Jun 1, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/charts/echarts")
public class EchartsController extends BaseController {
	private Map<String, String> chatMap = new HashMap<String, String>();
	
	@Autowired
	ILostVehicleService iLostVehicleService;
	
	@Autowired
	IVehicleRecordService iVehicleRecordService;

	@PostConstruct
	public void initChatMap(){
		chatMap.put("bar", "柱状图");
		chatMap.put("line", "折线图");
		chatMap.put("pie", "饼状图");
	}

	/**
	 * 
	 * @title: chart
	 * @description: 统计
	 * @param charttype
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("/chart/{charttype}")
	public String chart(@PathVariable("charttype") String charttype, HttpServletRequest request) {
		request.setAttribute("charttype", charttype);
		request.setAttribute("charttitle", chatMap.get(charttype));
		return display("echart");
	}
	

	@ResponseBody
	@RequestMapping(value = "/data/{charttype}", method = RequestMethod.POST)
	public R data(@PathVariable("charttype") String charttype, Queryable queryable, HttpServletRequest request) {
		R r = new R();
		int aleadyFindConunt = 0;
		int notFindConunt = 0;
		if("pie".equals(charttype)) {
			List<LostVehicle> lostVehicleList = iLostVehicleService.listWithNoPage(queryable);
			for(LostVehicle lostVehicle : lostVehicleList) {
				if(lostVehicle != null) {
					if("2".equals(lostVehicle.getLostStatus())) {
						aleadyFindConunt++;
					}else {
						notFindConunt++;
					}
				}
			}
			r.put("aleadyFindConunt", aleadyFindConunt);
			r.put("notFindConunt", notFindConunt);
		}else if("bar".equals(charttype)){
			List<VehicleRecord> vehicleRecordList = iVehicleRecordService.listWithNoPage(queryable);
			r.put("vehicleRecordList", vehicleRecordList);
		}
		return r;
	}
}