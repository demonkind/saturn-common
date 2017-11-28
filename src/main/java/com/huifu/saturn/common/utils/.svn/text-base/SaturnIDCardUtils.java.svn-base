/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * <h>身份证工具类</h>
 * 
 * @author zhanghaijie
 * @version $Id: SaturnIDCardUtils.java, v 0.1 2012-9-4 上午11:11:23 zhanghaijie Exp $
 */
public class SaturnIDCardUtils {

    /**
     * 身份证由18位转换成15位
     * 
     * @param idCard
     * @return
     */
    public static String convertIDCardFrom18To15(String idCard) {
        Assert.notNull(idCard);
        idCard = idCard.trim();
        Assert.isTrue(idCard.length() == 18);
        return new StringBuilder().append(idCard.substring(0, 6)).append(idCard.substring(8, 17))
            .toString();
    }

    /**
     * 身份证由15位转换为18位
     * 
     * @param idCard
     * @return
     */
    public static String convertIDCardFrom15To18(String idCard) {
        Assert.notNull(idCard);
        idCard = idCard.trim();
        Assert.isTrue(idCard.length() == 15);
        String ret = StringUtils.EMPTY;
        //15位的身份证号一定是2000年之前颁发的，年份上补齐19
        ret = ret.concat(idCard.substring(0, 6)).concat("19").concat(idCard.substring(6, 15));
        //计算第18位校验位
        char[] code = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; //11个
        int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };//18个;
        int[] idcd = new int[18];
        int i;
        int sum;
        int mod;

        sum = 0;
        for (i = 0; i < 17; i++) {
            idcd[i] = Integer.parseInt(ret.substring(i, i + 1));
            sum = sum + idcd[i] * factor[i];
        }
        mod = sum % 11;
        return ret.concat(String.valueOf(code[mod]));
    }
}
