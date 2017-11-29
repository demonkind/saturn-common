/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.log;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author su.zhang
 * @version $Id: LogInterceptor.java, v 0.1 2012-10-19 下午02:59:50 su.zhang Exp $
 */
public class LogInterceptor {
    
    final static Logger logger = LoggerFactory.getLogger("interceptor-log");
    
    public void before(JoinPoint joinpoint){
        Object [] arr=joinpoint.getArgs();//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        logger.info("调用类 :"+joinpoint.getSignature());
        for(Object o:arr){
            if(o!=null){
                logger.info(o.toString());
            }else{
                logger.info(null);
            }
           
        }
       
    }
    public void after(JoinPoint joinpoint,Object retValue){
        if(retValue!=null){
            logger.info("返回:"+retValue.toString());
        }else{
            logger.info("返回:null");
        }
      
}

}
