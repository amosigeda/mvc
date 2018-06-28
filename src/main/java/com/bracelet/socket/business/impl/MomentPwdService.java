package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.FingerDto;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.entity.BindDevice;
import com.bracelet.entity.MomentPwdInfo;
import com.bracelet.exception.BizException;
import com.bracelet.service.IMomentPwdService;
import com.bracelet.service.IOpenDoorService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.service.IVoltageService;
import com.bracelet.socket.business.IService;
import com.bracelet.util.PushUtil;
import com.bracelet.util.RespCode;

/**
 * 一次性密码
 * 
 */
@Component("momentPwdService")
public class MomentPwdService implements IService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IVoltageService voltageService;
	@Autowired
	ITokenInfoService tokenInfoService;
	@Autowired
	IPushlogService pushlogService;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IMomentPwdService momentPwdService;
	@Autowired
	IOpenDoorService opendoorService;

	@Override
	public SocketBaseDto process(JSONObject jsonObject, Channel incoming) {

		logger.info("===一次性密码的使用：" + jsonObject.toJSONString());
		JSONObject jsonObject2 = (JSONObject) jsonObject.get("data");
		if (jsonObject2 == null) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}

		String no = jsonObject.getString("no");
		String imei = jsonObject.getString("imei");

		Integer password = jsonObject2.getInteger("password");

		momentPwdService.updateStatus(imei, password, 1);

		MomentPwdInfo mmpwdInfo = momentPwdService.getByImeiAndPwd(imei,
				password);

		BindDevice bindd = userInfoService.getBindInfoByImeiAndStatus(imei, 1);

		FingerDto sosDto = new FingerDto();
		sosDto.setName(bindd.getName());
		sosDto.setImei(imei);
		sosDto.setTimestamp(new Date().getTime());
		String target = tokenInfoService.getTokenByUserId(mmpwdInfo
				.getUser_id());
		String title = "一次性密码的使用";
		String content = JSON.toJSONString(sosDto);
		String notifyContent = "门锁" + bindd.getName() + "被一次性密码" + password
				+ "打开,请知悉!";
		PushUtil.push(target, title, content, notifyContent);
		// save push log
		this.pushlogService.insert(mmpwdInfo.getUser_id(), imei, 0, target,
				title, content);
		
		opendoorService.insert(1, mmpwdInfo.getUser_id(), 5, 2, imei, "");
		
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(no);
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);

		return dto;

	}
}
