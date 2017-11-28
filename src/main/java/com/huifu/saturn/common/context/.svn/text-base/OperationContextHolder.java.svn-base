/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.context;

/**
 * 操作上下文容器，跟当前线程绑定
 * @author su.zhang
 * @version $Id: OperationContextHolder.java, v 0.1 2012-9-22 上午08:41:31 su.zhang Exp $
 */
public class OperationContextHolder {

    public static final String       operationContextSessionKey = "operationContextSessionKey";

    private static final ThreadLocal threadLocal                = new ThreadLocal();

    public static OperationContext get() {
        return (OperationContext) threadLocal.get();
    }

    public static void setOperationContext(OperationContext context) {
        threadLocal.set(context);
    }

}
