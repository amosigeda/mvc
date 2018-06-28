package com.bracelet.service;

import java.util.List;

import com.bracelet.entity.DeviceSetInfo;
import com.bracelet.entity.FlyInfo;
import com.bracelet.entity.PigeonsAndDove;
import com.bracelet.entity.PigeonsAndRing;

public interface IPigeonLoopService {

	boolean insertPigeonInfo(String footRingCode, String userObjId, String pigeonLineage, String plumageColor, String pigeonBirthday,
			String pigeonSex, String eyeSand, String token);

	List<PigeonsAndRing> getPigeonsAndRingWithList(String userObjId);

	boolean deletePigeonsByIds(String id);

	boolean insertRingDoveInfo(String ringCode, String userObjId, String token);

	List<PigeonsAndDove> getPigeonsAndDoveList(String userObjId);

	boolean deletePigeonsDoveByIds(String id);

	boolean insertBindInfo(String userPhone, String pigeonObjId, String ringObjId);

	boolean deletePigeonAndRingUnbind(String userPhone, String pigeonObjId);

	boolean insertThriftPowerConfig(String DEVICE_SERIAL_NUMBER, String rING_CODE, String pOSITION_MODE,
			String rEPORTED_FREQ);

	DeviceSetInfo getDeviceSetInfo(String dEVICE_SERIAL_NUMBER);

	boolean updateDevSetInfo(Long id, String bOOTTIME, String sDTIME);

	boolean insertTimingSwitchInfo(String dEVICE_SERIAL_NUMBER, String bOOTTIME, String sDTIME);

	boolean insertStartFlyingInfo(String dEVICE_SERIAL_NUMBER, String pIGEON_OBJ_ID, String rING_CODE);

	FlyInfo getFlyInfoInfo(String dEVICE_SERIAL_NUMBER, String pIGEON_OBJ_ID, String rING_CODE);

	boolean updateFlyInfo(Long id);

	List<FlyInfo> getRouteWithList(String dEVICE_SERIAL_NUMBER, String pIGEON_OBJ_ID, String rING_CODE,
			Integer rESNUMBER);

}
