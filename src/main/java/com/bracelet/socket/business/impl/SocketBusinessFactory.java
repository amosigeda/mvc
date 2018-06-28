package com.bracelet.socket.business.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bracelet.exception.BizException;
import com.bracelet.util.RespCode;
import com.bracelet.socket.business.IService;

/**
 * 业务类型工厂类,根据type返回对应的业务处理对象
 * 
 */
@Component
public class SocketBusinessFactory {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 根据名称注入对应的业务
     */
    @Resource
    private IService loginService;
   
    @Resource
    private IService opendoorService;
    @Resource
    private IService fingerService;
    @Resource
    private IService prizeupdoorService;
    @Resource
    private IService cleardataService;
    @Resource
    private IService voltageService;
    @Resource
    private IService momentPwdService;
    public IService getService(int type) throws BizException {
        logger.info("*****type:" + type);
        switch (type) {
        case 1:
            // 登录
            return loginService;
        case 11:
            // 开锁记录
            return opendoorService;
        case 12:
            // 指纹记录
            return fingerService;
        case 13:
            // 开锁记录
            return prizeupdoorService;
        case 14:
            // 清除信息
            return cleardataService;
        case 15:
            // ####低电量提醒(电量低至10%会发一次)
            return voltageService;
        case 16:
            // ####临时密码开锁
            return momentPwdService;
        default:
            logger.info("找不到对应的类型:" + type);
            throw new BizException(RespCode.DEV_REQ_TYPE_ERR);
        }
    }
}
