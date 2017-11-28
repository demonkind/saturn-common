/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;

/**
 * 转账业务类型
 * 
 * @author zhanghaijie
 * @version $Id: TransferBizType.java, v 0.1 2012-9-17 下午04:22:54 zhanghaijie Exp $
 */
public enum TransferBizType {

    INT("INT", "内部转账"),

    OUT("OUT", "外部转账"),

    PR("PR", "代发"),
    
    LICAI("LICAI", "存单支取");;

    /**
     * @param code
     * @param desc
     */
    private TransferBizType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /** 转账业务类型 */
    private String code;

    /** 业务描述 */
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
    public static TransferBizType valueByCode(String code) {
        for (TransferBizType bizType : TransferBizType.values()) {
            if (bizType.getCode().equals(code)) {
                return bizType;
            }
        }
        return null;
    }
    

}
