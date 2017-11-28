/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;

/**
 * 取现业务类型
 * 
 * @author zhanghaijie
 * @version $Id: WithdrawalBizType.java, v 0.1 2012-9-17 下午04:28:47 zhanghaijie Exp $
 */
public enum WithdrawalBizType {

    AUTO("AUTO", "自动取现（结算）"),

    SELF("SELF", "主动取现，用户发起的取现"),

    BSP("BSP", "BSP还款"),

    PR("PR", "代发"),

    LICAI("LICAI", "存单支取"),

    RET("RET", "退汇");

    /**
     * @param code
     * @param desc
     */
    private WithdrawalBizType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /** 取现类型 */
    private String code;

    /** 取现描述 */
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param code
     * @return
     */
    public static WithdrawalBizType valueByCode(String code) {
        for (WithdrawalBizType bizType : WithdrawalBizType.values()) {
            if (bizType.getCode().equals(code)) {
                return bizType;
            }
        }
        return null;
    }

}
