package com.bracelet.entity;

import java.sql.Timestamp;

public class FlyInfo {
	private Long id;
	private String device_serial_number;
	private String pigeon_obj_id;
	private String ring_code;
	private Timestamp starttime;
	private Timestamp endtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDevice_serial_number() {
		return device_serial_number;
	}
	public void setDevice_serial_number(String device_serial_number) {
		this.device_serial_number = device_serial_number;
	}
	public String getPigeon_obj_id() {
		return pigeon_obj_id;
	}
	public void setPigeon_obj_id(String pigeon_obj_id) {
		this.pigeon_obj_id = pigeon_obj_id;
	}
	public String getRing_code() {
		return ring_code;
	}
	public void setRing_code(String ring_code) {
		this.ring_code = ring_code;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	

}
