package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.FingerDto;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.BindDevice;
import com.bracelet.entity.NotRegisterInfo;
import com.bracelet.entity.UserInfo;
import com.bracelet.exception.BizException;
import com.bracelet.util.PushUtil;
import com.bracelet.util.RespCode;
import com.bracelet.util.SmsUtil;
import com.bracelet.service.IFingerService;
import com.bracelet.service.IMemService;
import com.bracelet.service.IMomentPwdService;
import com.bracelet.service.IOpenDoorService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.IPwdService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.business.IService;
import com.bracelet.util.ChannelMap;
import com.taobao.api.ApiException;

@Component("cleardataService")
public class ClearDataService implements IService {
	@Autowired
	IOpenDoorService opendoorService;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IMemService memService;
	@Autowired
	IFingerService fingerService;
	@Autowired
	IPwdService pwdService;
	
	@Autowired
	ITokenInfoService tokenInfoService;
	@Autowired
	IPushlogService pushlogService;
	
	@Autowired
	IMomentPwdService momentService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public SocketBaseDto process(JSONObject jsonObject, Channel channel) {
		logger.info("===设备清楚数据：" + jsonObject.toJSONString());
		String no = jsonObject.getString("no");
		String imei = jsonObject.getString("imei");
		
		List<BindDevice> list = userInfoService.getBindInfoByImei(imei);
		if (list.size() > 0) {
			for (BindDevice info : list) {
				Long userId = info.getUser_id();
				String name = info.getName();
				UserInfo userinfo = userInfoService.getUserInfoById(userId);
				if (userinfo != null) {
					FingerDto sosDto = new FingerDto();
					sosDto.setName(name);
					sosDto.setImei(imei);
					sosDto.setTimestamp(new Date().getTime());
					String target = tokenInfoService.getTokenByUserId(userId);
					String title = "设备清除信息";
					String content = JSON.toJSONString(sosDto);
					String notifyContent = "您绑定的门锁"+name+"正在清除信息,请知悉!";
					PushUtil.push(target, title, content, notifyContent);
					// save push log
					this.pushlogService.insert(userId, imei, 0, target, title, content);
				}
			}
		}
		
		
           //获得imei后 清除所有绑定信息 ，成员信心， 指纹信息。		
		memService.deleteByImei(imei);
		fingerService.deleteByImei(imei);
		pwdService.deleteByImei(imei);
		userInfoService.deleteByImei(imei);
		opendoorService.deleteByImei(imei);
		momentService.deleteByImei(imei);
		
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(no);
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);

		return dto;

	}
}
