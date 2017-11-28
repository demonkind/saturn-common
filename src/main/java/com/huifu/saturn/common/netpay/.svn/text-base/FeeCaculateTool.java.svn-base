/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.netpay;

import com.huifu.saturn.common.model.Money;

/**
 * 
 * @author xinru.lu
 * @version $Id: FeeCaculateTool.java, v 0.1 2012-9-7 下午7:48:23 xinru.lu Exp $
 */
public class FeeCaculateTool {

    public Money getFeeAmt(String calMode, Money transAmt) throws IllegalArgumentException {
        Interpret interpret = new Interpret();

        String feeAmt = interpret.getFeeAmt(calMode, transAmt.toString());
        if (feeAmt == null) {
            throw new IllegalArgumentException("手续费计算异常");
        }
        return new Money(feeAmt);
    }
}
