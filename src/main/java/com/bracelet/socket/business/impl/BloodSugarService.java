package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.service.IBloodSugarService;

/**
 * 血糖业务
 * 
 */
@Component("bloodSugarService")
public class BloodSugarService extends AbstractBizService{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    IBloodSugarService bloodSugarService;

    public SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        logger.info("===血糖：" + jsonObject.toJSONString());
        Long user_id = socketLoginDto.getUser_id();
        for (int i = 0; i < jsonArray.size(); i++) {
            try {
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                long timestamp = jsonObject2.getLongValue("timestamp");
                Integer bloodSugar = jsonObject2.getInteger("bloodSugar");
                bloodSugarService.insert(user_id, bloodSugar,  new Timestamp(timestamp * 1000));
            } catch (Exception e) {
                logger.error("保存血糖数组数据，发生错误:" + jsonArray.toJSONString(), e);
            }
        }

        SocketBaseDto dto = new SocketBaseDto();
        dto.setType(jsonObject.getIntValue("type"));
        dto.setNo(jsonObject.getString("no"));
        dto.setTimestamp(new Date().getTime());
        dto.setStatus(0);
        return dto;
    }
}
