/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.context;

import java.io.Serializable;

/**
 * 登录上下文对象
 * @author su.zhang
 * @version $Id: OperationContext.java, v 0.1 2012-9-22 上午08:38:48 su.zhang Exp $
 */
public class OperationContext implements Serializable {

    /**  */
    private static final long serialVersionUID = -4102970643826471138L;
    //  当前操作servlet路径，比如：请求地址是：https://www.huifu.com/buser/1002/3005/， 那么servletPath=/1002/3005/
    private String            servletPath;
    //会员id
    private String            custId;
    //操作员id
    private String            operId;
    //当前操作路径产品id,(或者为一级权限id)可以为空
    private String            level1Id;
    //登录id
    private String            loginId;
    //账户类型，代表金账户或者银账户
    private String            acctMode;
    //登录名
    private String            usrName;
    //简称
    private String            shortName;
    //操作员名称
    private String            operName;

    /**
     * Getter method for property <tt>servletPath</tt>.
     * 
     * @return property value of servletPath
     */
    public String getServletPath() {
        return servletPath;
    }

    /**
     * Setter method for property <tt>servletPath</tt>.
     * 
     * @param servletPath value to be assigned to property servletPath
     */
    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    /**
     * Getter method for property <tt>custId</tt>.
     * 
     * @return property value of custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * Setter method for property <tt>custId</tt>.
     * 
     * @param custId value to be assigned to property custId
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * Getter method for property <tt>level1Id</tt>.
     * 
     * @return property value of level1Id
     */
    public String getLevel1Id() {
        return level1Id;
    }

    /**
     * Setter method for property <tt>level1Id</tt>.
     * 
     * @param level1Id value to be assigned to property level1Id
     */
    public void setLevel1Id(String level1Id) {
        this.level1Id = level1Id;
    }

    /**
     * Getter method for property <tt>loginId</tt>.
     * 
     * @return property value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for property <tt>loginId</tt>.
     * 
     * @param loginId value to be assigned to property loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Getter method for property <tt>acctMode</tt>.
     * 
     * @return property value of acctMode
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * Setter method for property <tt>acctMode</tt>.
     * 
     * @param acctMode value to be assigned to property acctMode
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    /**
     * Getter method for property <tt>usrName</tt>.
     * 
     * @return property value of usrName
     */
    public String getUsrName() {
        return usrName;
    }

    /**
     * Setter method for property <tt>usrName</tt>.
     * 
     * @param usrName value to be assigned to property usrName
     */
    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    /**
     * Setter method for property <tt>operId</tt>.
     * 
     * @param operId value to be assigned to property operId
     */
    public void setOperId(String operId) {
        this.operId = operId;
    }

    /**
     * Getter method for property <tt>operId</tt>.
     * 
     * @return property value of operId
     */
    public String getOperId() {
        return operId;
    }

    /**
     * Setter method for property <tt>shortName</tt>.
     * 
     * @param shortName value to be assigned to property shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Getter method for property <tt>shortName</tt>.
     * 
     * @return property value of shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Setter method for property <tt>operName</tt>.
     * 
     * @param operName value to be assigned to property operName
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * Getter method for property <tt>operName</tt>.
     * 
     * @return property value of operName
     */
    public String getOperName() {
        return operName;
    }

}
