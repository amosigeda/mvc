package com.bracelet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.bracelet.entity.DeviceSetInfo;
import com.bracelet.entity.FlyInfo;
import com.bracelet.entity.PigeonsAndDove;
import com.bracelet.entity.PigeonsAndRing;
import com.bracelet.service.IPigeonLoopService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/service")
public class PigeonLoopContorller {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	IPigeonLoopService ipigonLoopService;

	// 新增信鸽
	@ResponseBody
	@RequestMapping(value = "/addRingInfo", method = RequestMethod.POST)
	public String addRingInfo(@RequestBody String json) {
		logger.info("新增信鸽=" + json);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String footRingCode = jsonObject.getString("FOOT_RING_CODE");// 鸽环编号
		String userObjId = jsonObject.getString("USER_OBJ_ID");
		String pigeonLineage = jsonObject.getString("PIGEON_LINEAGE");// 血统
		String plumageColor = jsonObject.getString("PLUMAGE_COLOR");// 羽毛颜色
		String pigeonBirthday = jsonObject.getString("PIGEON_BIRTHDAY");// 鸽子生日
		String pigeonSex = jsonObject.getString("PIGEON_SEX");// 鸽子性别
		String eyeSand = jsonObject.getString("EYE_SAND");// 眼砂
		String token = jsonObject.getString("TOKEN");
		ipigonLoopService.insertPigeonInfo(footRingCode, userObjId, pigeonLineage, plumageColor, pigeonBirthday,
				pigeonSex, eyeSand, token);
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		return bb.toString();
	}

	// 获取信鸽列表
	@ResponseBody
	@RequestMapping(value = "/getPigeonsAndRingWithList", method = RequestMethod.POST)
	public String getPigeonsAndRingWithList(@RequestBody String json) {
		logger.info("获取信鸽列表=" + json);

		JSONObject jsonObject = JSONObject.fromObject(json);
		String userObjId = jsonObject.getString("USER_OBJ_ID");
		String token = jsonObject.getString("TOKEN");
		JSONArray jsonarray = new JSONArray();

		List<PigeonsAndRing> list = ipigonLoopService.getPigeonsAndRingWithList(userObjId);
		if (list.size() > 0) {
			for (PigeonsAndRing info : list) {
				JSONObject abc = new JSONObject();
				abc.put("FOOT_RING_CODE", info.getFoot_ring_code());
				abc.put("PIGEON_LINEAGE", info.getPigeon_lineage());
				abc.put("PLUMAGE_COLOR", info.getPlumage_color());
				abc.put("PIGEON_BIRTHDAY", info.getPigeon_birthday());
				abc.put("PIGEN_SEX", info.getPigeon_sex());
				abc.put("EYE_SAND", info.getEye_sand());
				abc.put("PIGEON_STATUS", "1");
				abc.put("PIGEON_SCORE", "1");
				abc.put("RING_OBJ_ID", info.getId() + "");
				abc.put("RING_CODE", "1");
				abc.put("id", info.getId() + "");
				jsonarray.add(abc);
				// abc.clear();

			}
		}
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		bb.put("List", jsonarray);
		return bb.toString();
	}

	// 删除信鸽
	@ResponseBody
	@RequestMapping(value = "/deletePigeonsByIds", method = RequestMethod.POST)
	public String deletePigeonsByIds(@RequestBody String json) {
		logger.info("删除信鸽=" + json);

		JSONObject jsonObject = JSONObject.fromObject(json);
		String id = jsonObject.getString("id");

		ipigonLoopService.deletePigeonsByIds(id);

		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);

		return bb.toString();
	}
	

	// 新增鸽环
	@ResponseBody
	@RequestMapping(value = "/addRingDoveInfo", method = RequestMethod.POST)
	public String addRingDoveInfo(@RequestBody String json) {
		logger.info("新增鸽环=" + json);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String ringCode = jsonObject.getString("RING_CODE");
		String userObjId = jsonObject.getString("USER_OBJ_ID");
		String token = jsonObject.getString("TOKEN");
		ipigonLoopService.insertRingDoveInfo(ringCode, userObjId, token);
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		return bb.toString();
	}

	// 获取鸽环列表
	@ResponseBody
	@RequestMapping(value = "/getRingAndDoveList", method = RequestMethod.POST)
	public String getRingAndDoveList(@RequestBody String json) {
		logger.info("获取信鸽列表=" + json);

		JSONObject jsonObject = JSONObject.fromObject(json);
		String userObjId = jsonObject.getString("USER_OBJ_ID");
		String token = jsonObject.getString("TOKEN");
		JSONArray jsonarray = new JSONArray();

		List<PigeonsAndDove> list = ipigonLoopService.getPigeonsAndDoveList(userObjId);
		if (list.size() > 0) {
			for (PigeonsAndDove info : list) {
				JSONObject abc = new JSONObject();
				abc.put("SPECIFICATION", "1");
				abc.put("SCHEDULE_POWER", 1);
				abc.put("START_DATE", "20180707");
				abc.put("RING_CODE", info.getRing_code());
				abc.put("POWER_WARN", "1");
				abc.put("POSITION_MODE", "1");
				abc.put("FOOT_RING_CODE", "1");
				abc.put("SIM_STATUS", "1");
				abc.put("RING_STATUS", "1");
				abc.put("OUTPUT_ADDRESS", "广东省深圳市南山区数码大厦");
				abc.put("REPORTED_FREQ", "1");
				abc.put("START_STATUS", "1");
				abc.put("MANUFACTURE_DATE", "20160606");
				abc.put("SF_VERSION", "0.1");
				abc.put("id", info.getId() + "");
				jsonarray.add(abc);
			}
		}
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		bb.put("List", jsonarray);
		return bb.toString();
	}

	// 删除鸽环
	@ResponseBody
	@RequestMapping(value = "/deletePigeonsDoveByIds", method = RequestMethod.POST)
	public String deletePigeonsDoveByIds(@RequestBody String json) {
		logger.info("删除鸽环=" + json);

		JSONObject jsonObject = JSONObject.fromObject(json);
		String id = jsonObject.getString("id");

		ipigonLoopService.deletePigeonsDoveByIds(id);

		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);

		return bb.toString();
	}

	// 信鸽与鸽环绑定
	@ResponseBody
	@RequestMapping(value = "/pigeonBingRingWithBatch", method = RequestMethod.POST)
	public String pigeonBingRingWithBatch(@RequestBody String json) {
		logger.info("信鸽与鸽环绑定=" + json);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String pigeonObjId = jsonObject.getString("PIGEON_OBJ_ID");
		String ringObjId = jsonObject.getString("RING_OBJ_ID");
		String token = jsonObject.getString("TOKEN");
		String userPhone = jsonObject.getString("USER_PHONE");

		ipigonLoopService.insertBindInfo(userPhone, pigeonObjId, ringObjId);
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		return bb.toString();
	}

	// 信鸽与鸽环解绑
	@ResponseBody
	@RequestMapping(value = "/pigeonAndRingUnbind", method = RequestMethod.POST)
	public String pigeonAndRingUnbind(@RequestBody String json) {
		logger.info("信鸽与鸽环解绑=" + json);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String pigeonObjId = jsonObject.getString("PIGEON_OBJ_ID");
		String token = jsonObject.getString("TOKEN");
		String userPhone = jsonObject.getString("USER_PHONE");

		ipigonLoopService.deletePigeonAndRingUnbind(userPhone, pigeonObjId);
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		return bb.toString();
	}

	// 设置省电模式
	@ResponseBody
	@RequestMapping(value = "/thriftPowerConfig", method = RequestMethod.POST)
	public String thriftPowerConfig(@RequestBody String json) {
		logger.info("信鸽与鸽环解绑=" + json);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String RING_CODE = jsonObject.getString("RING_CODE");
		String POSITION_MODE = jsonObject.getString("POSITION_MODE");
		String REPORTED_FREQ = jsonObject.getString("REPORTED_FREQ");
		String DEVICE_SERIAL_NUMBER = jsonObject.getString("DEVICE_SERIAL_NUMBER");

		String token = jsonObject.getString("TOKEN");

		ipigonLoopService.insertThriftPowerConfig(DEVICE_SERIAL_NUMBER, RING_CODE, POSITION_MODE, REPORTED_FREQ);
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		return bb.toString();
	}

	// 16.定时开关设置
	@ResponseBody
	@RequestMapping(value = "/timingSwitch", method = RequestMethod.POST)
	public String timingSwitch(@RequestBody String json) {
		logger.info("定时开关设置=" + json);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String RING_CODE = jsonObject.getString("RING_CODE");
		String BOOTTIME = jsonObject.getString("BOOTTIME");
		String SDTIME = jsonObject.getString("SDTIME");
		String DEVICE_SERIAL_NUMBER = jsonObject.getString("DEVICE_SERIAL_NUMBER");

		String token = jsonObject.getString("TOKEN");

		DeviceSetInfo devInfo = ipigonLoopService.getDeviceSetInfo(DEVICE_SERIAL_NUMBER);
		if (devInfo != null) {
			ipigonLoopService.updateDevSetInfo(devInfo.getId(), BOOTTIME, SDTIME);
		} else {
			ipigonLoopService.insertTimingSwitchInfo(DEVICE_SERIAL_NUMBER, BOOTTIME, SDTIME);
		}
		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		return bb.toString();
	}

	// 开始飞行
	@ResponseBody
	@RequestMapping(value = "/startFlying", method = RequestMethod.POST)
	public String startFlying(@RequestBody String json) {
		logger.info("开始飞行=" + json);
		JSONObject jsonObject = JSONObject.fromObject(json);

		String PIGEON_OBJ_ID = jsonObject.getString("PIGEON_OBJ_ID");
		String RING_CODE = jsonObject.getString("RING_CODE");
		String DEVICE_SERIAL_NUMBER = jsonObject.getString("DEVICE_SERIAL_NUMBER");

		String token = jsonObject.getString("TOKEN");

		ipigonLoopService.insertStartFlyingInfo(DEVICE_SERIAL_NUMBER, PIGEON_OBJ_ID, RING_CODE);

		JSONObject bb = new JSONObject();
		bb.put("CODES", 200);
		return bb.toString();
	}

	//结束飞行
	@ResponseBody
	@RequestMapping(value = "/endFlying", method = RequestMethod.POST)
	public String endFlying(@RequestBody String json) {
		logger.info("结束飞行=" + json);
		JSONObject bb = new JSONObject();
	
		
		JSONObject jsonObject = JSONObject.fromObject(json);

		String PIGEON_OBJ_ID = jsonObject.getString("PIGEON_OBJ_ID");
		String RING_CODE = jsonObject.getString("RING_CODE");
		String DEVICE_SERIAL_NUMBER = jsonObject.getString("DEVICE_SERIAL_NUMBER");

		String token = jsonObject.getString("TOKEN");
		
		FlyInfo devInfo = ipigonLoopService.getFlyInfoInfo(DEVICE_SERIAL_NUMBER,PIGEON_OBJ_ID,RING_CODE);
		if(devInfo != null && devInfo.getEndtime() ==null || "".equals(devInfo.getEndtime())){
			ipigonLoopService.updateFlyInfo(devInfo.getId());
			bb.put("CODES", 200);
		}else{
			bb.put("CODES", 500);
		}
		return bb.toString();
	}
	
    	//12.查询信鸽行程列表
		@ResponseBody
		@RequestMapping(value = "/getRouteWithList", method = RequestMethod.POST)
		public String getRouteWithList(@RequestBody String json) {
			logger.info("查询信鸽行程列表=" + json);
			JSONObject bb = new JSONObject();
		
			
			JSONObject jsonObject = JSONObject.fromObject(json);

			String PIGEON_OBJ_ID = jsonObject.getString("PIGEON_OBJ_ID");
			String RING_CODE = jsonObject.getString("RING_CODE");
			String DEVICE_SERIAL_NUMBER = jsonObject.getString("DEVICE_SERIAL_NUMBER");
			String USER_OBJ_ID = jsonObject.getString("USER_OBJ_ID");

			String token = jsonObject.getString("TOKEN");
			Integer RESNUMBER = jsonObject.getInt("RESNUMBER");
			
			JSONArray jsonArray = new JSONArray();
			List<FlyInfo> devInfoList = ipigonLoopService.getRouteWithList(DEVICE_SERIAL_NUMBER,PIGEON_OBJ_ID,RING_CODE ,RESNUMBER);
			if(devInfoList.size() > 0){
                for (FlyInfo info : devInfoList) {
                	JSONObject abc = new JSONObject();
                	abc.put("USER_OBJ_ID", USER_OBJ_ID);
                	abc.put("ROUTE_NAME", "");
                	abc.put("ROUTE_START_ADDRESS", "");
                	abc.put("ROUTE_START_TIME", info.getStarttime());
                	abc.put("ROUTE_END_ADDRESS", "");
                	abc.put("ROUTE_END_TIME", info.getEndtime());
                	abc.put("ROUTE_ID", info.getId());
                	jsonArray.add(abc);
				}
			}
			
			bb.put("List", jsonArray);
			bb.put("CODES", 200);
			
			return bb.toString();
		}

}
