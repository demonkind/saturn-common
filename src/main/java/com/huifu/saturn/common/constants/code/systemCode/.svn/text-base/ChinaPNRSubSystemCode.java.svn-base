/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.systemCode;

/**
 * 子系统号
 * @author zhanghaijie
 * @version $Id: ChinaPNRSubSystemCode.java, v 0.1 2012-9-18 下午03:58:54 zhanghaijie Exp $
 */
public enum ChinaPNRSubSystemCode {

    ACCT("ACCT", ChinaPNRSystemCode.ACCT, "账务主系统"),

    ACCTMAG("ACCTMAG", ChinaPNRSystemCode.ACCT, "账务管理系统"),

    ACCTSERVICE("ACCTSERVICE", ChinaPNRSystemCode.ACCT, "账务服务"),

    BUSER("BUSER", ChinaPNRSystemCode.BUSER, "金账户平台"),

    BUSERMAG("BUSERMAG", ChinaPNRSystemCode.BUSER, "金账户管理系统"),

    CASH("CASH", ChinaPNRSystemCode.CASH, "收银台"),

    CASHMAG("CASHMAG", ChinaPNRSystemCode.CASH, "收银台管理系统"),

    UBS("UBS", ChinaPNRSystemCode.UBS, "用户基础服务平台"),

    UBSMAG("UBSMAG", ChinaPNRSystemCode.UBS, "UBS管理系统"),

    RBS("RBS", ChinaPNRSystemCode.RBS, "基础收单平台"),

    RBSMAG("RBSMAG", ChinaPNRSystemCode.RBS, "基础收单平台控台"),

    RECV("RECV", ChinaPNRSystemCode.RECV, "网上收单"),

    POS("POS", ChinaPNRSystemCode.POS, "POS收单"),

    MAG("MAG", ChinaPNRSystemCode.MAG, "内部管理平台");

    /**
     * @param subSysCode
     * @param sysCode
     * @param desc
     */
    private ChinaPNRSubSystemCode(String subSysCode, ChinaPNRSystemCode sysCode, String desc) {
        this.subSysCode = subSysCode;
        this.sysCode = sysCode;
        this.desc = desc;
    }

    /**子系统代号  */
    private String             subSysCode;

    /**系统代号  */
    private ChinaPNRSystemCode sysCode;

    /** 子系统说明 */
    private String             desc;

    /**
     * 
     * @return
     */
    public String getSubSysCode() {
        return subSysCode;
    }

    public void setSubSysCode(String subSysCode) {
        this.subSysCode = subSysCode;
    }

    public ChinaPNRSystemCode getSysCode() {
        return sysCode;
    }

    public void setSysCode(ChinaPNRSystemCode sysCode) {
        this.sysCode = sysCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
