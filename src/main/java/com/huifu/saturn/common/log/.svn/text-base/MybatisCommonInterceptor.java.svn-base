/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.log;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author su.zhang
 * @version $Id: MybatisCommonInterceptor.java, v 0.1 2012-10-19 下午01:52:27 su.zhang Exp $
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class MybatisCommonInterceptor implements Interceptor {

    final static Logger logger = LoggerFactory.getLogger("sql-log");

    /** 
     * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        BoundSql boundSql = statementHandler.getBoundSql();

        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);

        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        String params = "";
        Object o = boundSql.getParameterObject();
        if (o != null) {
            if (o instanceof String) {
                params = o.toString();
            } else {
                params = ToStringBuilder.reflectionToString(o);
            }
        }

        logger.info("invoked sql:{},params:{}", originalSql, params);
        return invocation.proceed();
    }

    /** 
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /** 
     * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties properties) {
    }

}
