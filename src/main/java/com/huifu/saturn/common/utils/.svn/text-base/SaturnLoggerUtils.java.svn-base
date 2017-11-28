/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.utils;

import org.apache.log4j.Logger;

/**
 * <h>日志封装</h>
 * 
 * <br>
 * 调用方式SanturnLoggerUtils.info(logger,message);
 * 
 * @author zhanghaijie
 * @version $Id: SaturnLoggerUtils.java, v 0.1 2012-8-22 上午8:46:54 zhanghaijie Exp $
 */
public class SaturnLoggerUtils {

    /**
     * Info 级别日志
     * 
     * @param logger
     */
    public static void info(Logger logger, String... messages) {
        if (null != logger && logger.isInfoEnabled()) {
            String message = assembleMessage(messages);
            logger.info(message);
        }
    }

    /**
     * Debug级别日志
     * 
     * @param logger
     * @param message
     */
    public static void debug(Logger logger, String... messages) {
        if (null != logger && logger.isDebugEnabled()) {
            String message = assembleMessage(messages);
            logger.debug(message);
        }
    }

    /**
     * Warn级别日志
     * 
     * @param logger
     * @param messages
     */
    public static void warn(Logger logger, Throwable t, String... messages) {
        if (null != logger) {
            String message = assembleMessage(messages);
            logger.warn(message, t);
        }
    }

    /**
     * Error级别日志
     * 
     * @param logger
     * @param t
     * @param messages
     */
    public static void error(Logger logger, Throwable t, String... messages) {
        if (null != logger) {
            String message = assembleMessage(messages);
            logger.error(message, t);
        }
    }

    /**
     * 字符串拼装
     * 
     * @param messages
     * @return
     */
    private static String assembleMessage(String... messages) {
        StringBuffer buffer = new StringBuffer();
        if (null != messages) {
            for (String message : messages) {
                buffer.append(message);
                buffer.append(" ");
            }
        }
        return buffer.toString();
    }

}
