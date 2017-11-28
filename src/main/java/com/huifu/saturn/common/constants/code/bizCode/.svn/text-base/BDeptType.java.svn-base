/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;

/**
 * 事业部代号
 * 
 * @author zhanghaijie
 * @version $Id: BDeptType.java, v 0.1 2012-10-19 上午10:07:22 zhanghaijie Exp $
 */
public enum BDeptType {

    HL("HL", "航旅"),

    LT("LT", "流通"),

    LC("LC", "理财"),

    SD("SD", "收单"),

    JR("JR", "金融"),

    HD("HD", "总部");

    private String code;

    private String desc;

    private BDeptType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * @param code
     * @return
     */
    public static BDeptType valueByCode(String code) {
        for (BDeptType bDeptType : BDeptType.values()) {
            if (bDeptType.getCode().equals(code)) {
                return bDeptType;
            }
        }
        return null;
    }

}
