package com.bracelet.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bracelet.entity.BloodOxygen;
import com.bracelet.entity.DeviceSetInfo;
import com.bracelet.entity.FlyInfo;
import com.bracelet.entity.PigeonsAndDove;
import com.bracelet.entity.PigeonsAndRing;
import com.bracelet.service.IPigeonLoopService;
import com.bracelet.util.Utils;

import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PigeonLoopServiceImpl implements IPigeonLoopService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(getClass());
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public boolean insertPigeonInfo(String footRingCode, String userObjId, String pigeonLineage, String plumageColor,
			String pigeonBirthday, String pigeonSex, String eyeSand, String token) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into pigeon_race_info (user_obj_id, foot_ring_code, pigeon_lineage, plumage_color, pigeon_birthday, pigeon_sex, eye_sand, token ,createtime) values (?,?,?,?,?,?,?,?,?)",
				new Object[] { userObjId , footRingCode , pigeonLineage, plumageColor, pigeonBirthday, pigeonSex, eyeSand,token ,now }, new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
	}

	@Override
	public List<PigeonsAndRing> getPigeonsAndRingWithList(String userObjId) {
		String sql = "select * from pigeon_race_info where user_obj_id = ?";
		List<PigeonsAndRing> list = jdbcTemplate.query(sql, new Object[] { userObjId },
				new BeanPropertyRowMapper<PigeonsAndRing>(PigeonsAndRing.class));
		return list;
	}

	@Override
	public boolean deletePigeonsByIds(String id) {
		int i = jdbcTemplate.update(
				"delete from pigeon_race_info where id = ?",
				new Object[] { id}, new int[] { Types.VARCHAR});
		return i == 1;
	}

	@Override
	public boolean insertRingDoveInfo(String ringCode, String userObjId, String token) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into pigeon_ring_dove_info (user_obj_id, ring_code, token ,createtime) values (?,?,?,?)",
				new Object[] { userObjId , ringCode , token , now }, new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
	}

	@Override
	public List<PigeonsAndDove> getPigeonsAndDoveList(String userObjId) {
		String sql = "select * from pigeon_ring_dove_info where user_obj_id = ?";
		List<PigeonsAndDove> list = jdbcTemplate.query(sql, new Object[] { userObjId },
				new BeanPropertyRowMapper<PigeonsAndDove>(PigeonsAndDove.class));
		return list;
	}

	@Override
	public boolean deletePigeonsDoveByIds(String id) {
		int i = jdbcTemplate.update(
				"delete from pigeon_ring_dove_info where id = ?",
				new Object[] { id}, new int[] { Types.VARCHAR});
		return i == 1;
	}

	@Override
	public boolean insertBindInfo(String userPhone, String pigeonObjId, String ringObjId) {

		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into app_bind_info (user_phone, pigeon_obj_id, ring_obj_id ,createtime) values (?,?,?,?)",
				new Object[] { userPhone , pigeonObjId , ringObjId , now }, new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
	
	}

	@Override
	public boolean deletePigeonAndRingUnbind(String userPhone, String pigeonObjId) {
		int i = jdbcTemplate.update(
				"delete from app_bind_info where userPhone = ? and  pigeon_obj_id = ?",
				new Object[] { userPhone, pigeonObjId}, new int[] { Types.VARCHAR, Types.VARCHAR});
		return i == 1;
	}

	@Override
	public boolean insertThriftPowerConfig(String DEVICE_SERIAL_NUMBER, String rING_CODE, String pOSITION_MODE,
			String rEPORTED_FREQ) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into device_set (DEVICE_SERIAL_NUMBER, LOCATION_TYPE, POINT_FREQUENCY, GET_TIME, GET_STATUS) values (?,?,?,?,?)",
				new Object[] { DEVICE_SERIAL_NUMBER , pOSITION_MODE , rEPORTED_FREQ, formatter.format(new Date()), 0 }, new int[] { Types.VARCHAR, Types.INTEGER,
						Types.INTEGER, Types.VARCHAR, Types.INTEGER});
		return i == 1;
	}

	@Override
	public DeviceSetInfo getDeviceSetInfo(String dEVICE_SERIAL_NUMBER) {
        String sql = "select * from device_set where DEVICE_SERIAL_NUMBER=? order by id desc LIMIT 1";
        List<DeviceSetInfo> list = jdbcTemplate.query(sql, new Object[] { dEVICE_SERIAL_NUMBER }, 
        		new BeanPropertyRowMapper<DeviceSetInfo>(DeviceSetInfo.class));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            logger.info("getDeviceSetInfo reutrn null.user_id:" + dEVICE_SERIAL_NUMBER);
        }
        return null;
    }

	@Override
	public boolean updateDevSetInfo(Long id, String bOOTTIME, String sDTIME) {
		
		int i = jdbcTemplate
				.update("update device_set set TING_OFF_ON = ?,UPDATE_TIME=? , GET_STATUS = 0 where id = ?",
						new Object[] { bOOTTIME+"|"+sDTIME , formatter.format(new Date()), id}, new int[] {
								Types.VARCHAR, Types.VARCHAR, Types.INTEGER });
		return i == 1;
	}

	@Override
	public boolean insertTimingSwitchInfo(String dEVICE_SERIAL_NUMBER, String bOOTTIME, String sDTIME) {
		int i = jdbcTemplate.update(
				"insert into device_set (DEVICE_SERIAL_NUMBER, TING_OFF_ON, UPDATE_TIME, GET_STATUS) values (?,?,?,?)",
				new Object[] { dEVICE_SERIAL_NUMBER , bOOTTIME+"|"+sDTIME , formatter.format(new Date()), 0 }, new int[] { 
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER});
		return i == 1;
	}

	@Override
	public boolean insertStartFlyingInfo(String dEVICE_SERIAL_NUMBER, String pIGEON_OBJ_ID, String rING_CODE) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into fly_info (device_serial_number, pigeon_obj_id, ring_code, starttime) values (?,?,?,?)",
				new Object[] { dEVICE_SERIAL_NUMBER , pIGEON_OBJ_ID , rING_CODE, now }, new int[] { 
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP});
		return i == 1;
	}

	@Override
	public FlyInfo getFlyInfoInfo(String dEVICE_SERIAL_NUMBER, String pIGEON_OBJ_ID, String rING_CODE) {
        String sql = "select * from fly_info where device_serial_number = ? and pigeon_obj_id =? and ring_code order by id desc LIMIT 1";
        List<FlyInfo> list = jdbcTemplate.query(sql, new Object[] { dEVICE_SERIAL_NUMBER,pIGEON_OBJ_ID,rING_CODE }, 
        		new BeanPropertyRowMapper<FlyInfo>(FlyInfo.class));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            logger.info("getFlyInfoInfo reutrn null.user_id:" + dEVICE_SERIAL_NUMBER);
        }
        return null;
    }

	@Override
	public boolean updateFlyInfo(Long id) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate
				.update("update fly_info set endtime = ?  where id = ?",
						new Object[] { now, id}, new int[] {
								Types.TIMESTAMP, Types.INTEGER });
		return i == 1;
	}

	@Override
	public List<FlyInfo> getRouteWithList(String dEVICE_SERIAL_NUMBER, String pIGEON_OBJ_ID, String rING_CODE,
			Integer rESNUMBER) {
		String sql = "select * from fly_info where device_serial_number = ? and pigeon_obj_id = ? and ring_code =? order by id desc limit " + rESNUMBER;
		List<FlyInfo> list = jdbcTemplate.query(sql, new Object[] { dEVICE_SERIAL_NUMBER, pIGEON_OBJ_ID ,rING_CODE },
				new BeanPropertyRowMapper<FlyInfo>(FlyInfo.class));
		return list;
	}



}
