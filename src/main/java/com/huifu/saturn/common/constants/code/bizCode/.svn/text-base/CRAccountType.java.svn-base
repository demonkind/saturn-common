/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;

/**
 * 贷记帐户类型
 * 
 * @author zhanghaijie
 * @version $Id: CRAccountType.java, v 0.1 2012-9-18 下午03:20:02 zhanghaijie Exp $
 */
public enum CRAccountType {

    BASECR("BASECR", "基本贷记帐户", "BASECR"),

    MERCR("MERCR", "商户专用信用帐户", "MCR"),

    SPECR("SPECR", "专用贷记账户", "SCR");

    /**
     * @param code
     * @param desc
     */
    private CRAccountType(String code, String desc, String subAcctHeader) {
        this.code = code;
        this.desc = desc;
        this.subAcctHeader = subAcctHeader;
    }

    /** 贷记账户类型 */
    private String code;

    /**  */
    private String desc;

    /** 子账户头 */
    private String subAcctHeader;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public String getSubAcctHeader() {
        return subAcctHeader;
    }

    /**
     * @param code
     * @return
     */
    public static CRAccountType valueByCode(String code) {
        for (CRAccountType accountType : CRAccountType.values()) {
            if (accountType.getCode().equals(code)) {
                return accountType;
            }
        }
        return null;
    }

}
