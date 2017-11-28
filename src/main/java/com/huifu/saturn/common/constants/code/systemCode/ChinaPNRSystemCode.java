/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.systemCode;

/**
 * 汇付天下系统代号
 * 
 * @author zhanghaijie
 * @version $Id: ChinaPNRSystemCode.java, v 0.1 2012-8-10 下午1:42:19 zhanghaijie Exp $
 */
public enum ChinaPNRSystemCode {

    /** 账务域-账务平台 */
    ACCT("ACCT", "帐务系统", ChinaPNRDomainCode.ACCT_DOMAIN),

    /** 支付域-支付平台 */
    PAY("PAY", "支付系统", ChinaPNRDomainCode.PAYMENT_DOMAIN),

    /** 支付域-汇款平台 */
    REM("REM", "汇款平台", ChinaPNRDomainCode.PAYMENT_DOMAIN),

    /** 会员域-用户基础服务平台 */
    UBS("UBS", "用户基础服务平台", ChinaPNRDomainCode.MEMBER_DOMAIN),

    /** 会员域-收银台 */
    CASH("CASH", "收银台", ChinaPNRDomainCode.MEMBER_DOMAIN),

    /** 门户域-金账户平台 */
    BUSER("BUSER", "金账户平台", ChinaPNRDomainCode.PORTAL_DOMAIN),

    /** 门户域-银账户平台 */
    CUSER("CUSER", "银账户平台", ChinaPNRDomainCode.PORTAL_DOMAIN),

    /** 收单域-基础收单平台 */
    RBS("RBS", "基础收单平台", ChinaPNRDomainCode.ACQUIRER_DOMAIN),

    /** 收单域-网上收单 */
    RECV("RECV", "网上收单", ChinaPNRDomainCode.ACQUIRER_DOMAIN),

    /** 收单域-POS收单 */
    POS("POS", "POS收单", ChinaPNRDomainCode.ACQUIRER_DOMAIN),

    /** 内部域-内部管理平台 */
    MAG("MAG", "内部管理平台", ChinaPNRDomainCode.INNER_DOMAIN),

    /** 内部域-报表中心 */
    RPT("RPT", "报表中心", ChinaPNRDomainCode.INNER_DOMAIN);

    /** 系统码 */
    private final String             systemCode;

    /** 系统名称 */
    private final String             systemName;

    /** 系统域 */
    private final ChinaPNRDomainCode domain;

    /**
     * @param systemCode
     * @param systemName
     * @param domain
     */
    private ChinaPNRSystemCode(String systemCode, String systemName, ChinaPNRDomainCode domain) {
        this.systemCode = systemCode;
        this.systemName = systemName;
        this.domain = domain;
    }

    /**
     * 获取系统码
     * 
     * @return
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 获取系统名称
     * 
     * @return
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * 获取系统所属域
     * 
     * @return
     */
    public ChinaPNRDomainCode getDomain() {
        return domain;
    }

}
