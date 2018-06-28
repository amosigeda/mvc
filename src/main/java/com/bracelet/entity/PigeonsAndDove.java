package com.bracelet.entity;

import java.sql.Timestamp;

public class PigeonsAndDove {
	private Long id;
	private String user_obj_id;
	private String ring_code;
	private String token;
	private Timestamp createtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_obj_id() {
		return user_obj_id;
	}
	public void setUser_obj_id(String user_obj_id) {
		this.user_obj_id = user_obj_id;
	}
	public String getRing_code() {
		return ring_code;
	}
	public void setRing_code(String ring_code) {
		this.ring_code = ring_code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	

}
