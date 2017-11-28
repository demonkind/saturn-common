/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.context.mag;

/**
 * 
 * @author su.zhang
 * @version $Id: OperationContext.java, v 0.1 2012-9-7 下午07:00:29 su.zhang Exp $
 */
public class OperationContextHolder {

    public static final String       operationContextSessionKey = "magOperationContextSessionKey";

    private static final ThreadLocal threadLocal                = new ThreadLocal();

    public static OperationContext get() {
        return (OperationContext) threadLocal.get();
    }

    public static void setOperationContext(OperationContext context) {
        threadLocal.set(context);
    }
}
