/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;

/**
 *  借记账户类型
 * 
 * @author zhanghaijie
 * @version $Id: AccountType.java, v 0.1 2012-9-17 下午04:59:46 zhanghaijie Exp $
 */
public enum DTAccountType {

    BASEDT("BASEDT", "基本借记户", "BASEDT"),

    RECV("RECV", "收款户", "RC"),

    PAY("PAY", "付款户", "PAY"),

    MERDT("MERDT", "商户专用借记帐户", "MDT"),

    FUND("FUND", "基金户", "FUND"),

    BUY("BUY", "申购户", "BUY"),

    RED("RED", "赎回户", "RED"),

    FAS("FAS", "基金代销户", "FAS"),

    INT("INT", "内部账户", "INT"),

    FC("FC", "外币账户", "FC"),

    TG("TG", "托管账户", "TG"),

    FS("FS", "基金份额账户", "FS"),

    PT("PT", "积分账户", "PT"),

    SPEDT("SPEDT", "专用借记账户", "SDT"),

    DEP("DEP", "保证金账户", "DEP");

    private DTAccountType(String code, String desc, String subAcctHeader) {
        this.code = code;
        this.desc = desc;
        this.subAcctHeader = subAcctHeader;
    }

    /** 账户类型码 */
    private String code;

    /** 账户说明 */
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
    public static DTAccountType valueByCode(String code) {
        for (DTAccountType accountType : DTAccountType.values()) {
            if (accountType.getCode().equals(code)) {
                return accountType;
            }
        }
        return null;
    }

}
