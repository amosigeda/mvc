package com.bracelet.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/test")
public class TestContorller {

	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String Post(@RequestBody String json) {
		logger.info(json);
		JSONObject jsonObject = (JSONObject) JSON.parse(json);
		
		System.out.println(jsonObject.getString("a"));
		
	
		JSONObject bb=new JSONObject();
		bb.put("CODES", 500);
		bb.put("CODEssS", 500);
		bb.put("CODEsS", 500);
		bb.put("CODEssssS", 500);
		return bb.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String register(@RequestParam String username,@RequestParam String password, ModelMap model) {
		
		if("admin".equals(username)  && "123456".equals(password)) {
			return "loginSuccess";
		}else {
			return "loginfail";
		}
		
	}

}
