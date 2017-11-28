/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;

/**
 * <h>性别常量</h>
 * 
 * @author zhanghaijie
 * @version $Id: SaturnSexConstants.java, v 0.1 2012-9-4 上午11:19:31 zhanghaijie Exp $
 */
public enum SaturnSexCode {

    MALE("M", "男性"),

    FEMALE("F", "女性"),

    UNKNOWN("U", "未知");

    /**
     * @param code
     * @param type
     */
    private SaturnSexCode(String code, String type) {
        this.code = code;
        this.type = type;
    }

    /** 性别代号 */
    private String code;

    /** 性别类型 */
    private String type;

    /**
     * 获取性别代号
     * 
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取性别类型
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 通过证件号获取对象
     * 
     * @param code
     * @return
     */
    public static SaturnSexCode valueByCode(String code) {
        for (SaturnSexCode sexCode : SaturnSexCode.values()) {
            if (sexCode.getCode().equals(code)) {
                return sexCode;
            }
        }
        return null;
    }
}
