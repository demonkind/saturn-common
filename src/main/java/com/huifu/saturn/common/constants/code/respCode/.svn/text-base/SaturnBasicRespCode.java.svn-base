/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.respCode;

/**
 * 系统间返回码
 * 
 * @author zhanghaijie
 * @version $Id: ChinaPNRReturnCode.java, v 0.1 2012-8-21 下午5:05:04 zhanghaijie Exp $
 */
public enum SaturnBasicRespCode {

    SUCCESS("000", "成功"),

    REPEAT_TRADE("001", "重复交易"),

    AUTHENTICATE_SIGN_ERROR("010", "验签失败"),

    CONNECTION_EXPIRED("011", "连接超时失效"),

    REQ_PARAMS_ILLEGAL("060", "请求参数非法"),

    REQ_IP_ILLEGAL("061", "请求IP非法"),

    REQ_PARAMS_CHECK_ERROR("062", "请求数据校验失败"),

    REQUEST_DATA_NOT_EXIST("070", "请求数据不存在"),

    AUTH_CODE_ERROR("071", "验证码验证失败"),

    INSUFFICIENT_PERMISSION("091", "权限不足"),

    DB_EXCEPTION("097", "数据库异常"),

    SYSTEM_TIME_OUT("098", "系统超时"),

    SYSTEM_EXCEPTION("099", "系统异常");

    /**
     * @param returnCode
     * @param codeDesc
     */
    private SaturnBasicRespCode(String returnCode, String codeDesc) {
        this.returnCode = returnCode;
        this.codeDesc = codeDesc;
    }

    /** 返回码 */
    private String returnCode;

    /** 返回码说明 */
    private String codeDesc;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

}
