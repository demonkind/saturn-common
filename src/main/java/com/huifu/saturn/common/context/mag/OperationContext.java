/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.context.mag;

import java.io.Serializable;

/**
 * 
 * @author su.zhang
 * @version $Id: OperationContext.java, v 0.1 2012-9-7 下午07:08:38 su.zhang Exp $
 */
public class OperationContext implements Serializable {

    /**  */
    private static final long serialVersionUID = -7261610544202548856L;
    //当前操作servlet路径，比如：请求地址是：https://www.huifu.com/101005/control， 那么servletPath=/101005/control
    private String            servletPath;
    //操作员id
    private String            operatorId;

    private String            bdepId;

    /**
     * Setter method for property <tt>servletPath</tt>.
     * 
     * @param servletPath value to be assigned to property servletPath
     */
    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    /**
     * Getter method for property <tt>servletPath</tt>.
     * 
     * @return property value of servletPath
     */
    public String getServletPath() {
        return servletPath;
    }

    /**
     * Getter method for property <tt>operatorId</tt>.
     * 
     * @return property value of operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * Setter method for property <tt>operatorId</tt>.
     * 
     * @param operatorId value to be assigned to property operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getBdepId() {
        return bdepId;
    }

    public void setBdepId(String bdepId) {
        this.bdepId = bdepId;
    }

}
