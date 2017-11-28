/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;

/**
 *交易类型
 * 
 * @author zhanghaijie
 * @version $Id: TransType.java, v 0.1 2012-9-17 下午05:20:20 zhanghaijie Exp $
 */
public enum TransType {

    TRANS_TYPE_1001("1001", "支付收款"),

    TRANS_TYPE_1002("1002", "充值"),

    TRANS_TYPE_1004("1004", "账户付款退款"),

    TRANS_TYPE_1006("1006", "转帐转入"),

    TRANS_TYPE_1008("1008", "支付收款退款失败"),

    TRANS_TYPE_1012("1012", "账务调账调入"),

    TRANS_TYPE_1025("1025", "信用还款入账"),

    TRANS_TYPE_1028("1028", "退款手续费返还"),

    TRANS_TYPE_1029("1029", "信用多余款项转入"),

    TRANS_TYPE_1036("1036", "取现失败"),

    TRANS_TYPE_1080("1080", "网关支付"),

    TRANS_TYPE_1081("1081", "利息结算"),

    TRANS_TYPE_2001("2001", "取现"),

    TRANS_TYPE_2002("2002", "账户付款"),

    TRANS_TYPE_2006("2006", "支付收款退款"),

    TRANS_TYPE_2007("2007", "转账转出"),

    TRANS_TYPE_2011("2011", "账务调账调出"),

    TRANS_TYPE_2013("2013", "信用账单滞纳金缴纳"),

    TRANS_TYPE_2014("2014", "信用还款"),

    TRANS_TYPE_2017("2017", "信用多余款项转出"),

    TRANS_TYPE_2018("2018", "手续费收取"),

    TRANS_TYPE_2070("2070", "充值退款"),

    RANS_TYPE_2080("2080", "网关支付退款"),

    TRANS_TYPE_2081("2081", "信用账单利息缴纳"),

    TRANS_TYPE_3002("3002", "转账"),

    TRANS_TYPE_9001("9001", "冻结"),

    TRANS_TYPE_9002("9002", "解冻");

    private TransType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /** 交易类型 */
    private String code;

    /** 交易类型描述 */
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
    public static TransType valueByCode(String code) {
        for (TransType transType : TransType.values()) {
            if (transType.getCode().equals(code)) {
                return transType;
            }
        }
        return null;
    }

}
