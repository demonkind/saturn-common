/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.constants.code.bizCode;


/**
 * <h>Saturn证件类型</h>
 * 
 * @author zhanghaijie
 * @version $Id: SaturnCertCode.java, v 0.1 2012-9-4 上午10:49:19 zhanghaijie Exp $
 */
public enum SaturnCertCode {

    ID_CARD("00", "身份证"),

    PASSPORT("01", "护照"),

    ARMY_IDENTITY_CARD("02", "军官证"),

    SOLDIERS_CARD("03", "士兵证"),

    RE_ENTRY_PERMIT("04", "回乡证"),

    HOUSEHOLD_REGISTER("05", "户口本"),

    FOREIGN_PASSPORT("06", "外国护照"),

    OTHER_PERSONAL_CERT("07", "其它个人证件类型"),

    CERTIFICATE_OF_ORGANIZATION_CODE("61", "组织机构代码证"),

    BUSINESS_LICENSE("62", "营业执照"),

    TAX_REGISTRATION_CERTIFICATE("63", "税务登记证"),

    OTHER_ORGANIZATION_CERTIFICATE("69", "其它机构证件");

    /**
     * @param code
     * @param type
     */
    private SaturnCertCode(String code, String type) {
        this.code = code;
        this.type = type;
    }

    /** 证件代号 */
    private String code;

    /** 证件类型 */
    private String type;

    /**
     * 获取证件代号
     * 
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取证件类型
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 通过证件号获取证件对象
     * @param code
     * @return
     */
    public static SaturnCertCode valueByCode(String code) {
        for (SaturnCertCode certCode : SaturnCertCode.values()) {
            if (certCode.getCode().equals(code)) {
                return certCode;
            }
        }
        return null;
    }

}
