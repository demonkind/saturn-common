/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

/**
 * 产生任意长度的随机串
 * @author su.zhang
 * @version $Id: UniqRandom.java, v 0.1 2012-9-27 下午08:08:34 su.zhang Exp $
 */
public class UniqRandom {

    /**
     * 产生任意长度的随机串
     * 
     * @param length 长度
     * @return
     */
    private static String generate(int length) {

        Random rnd = null;
        try {
            rnd = new SecureRandom();
        } catch (Throwable e) {
            rnd = new Random();
        }

        byte[] bytes = new byte[((length + 3) / 4) * 3];

        rnd.nextBytes(bytes);
        String s = new String(Base64.encodeBase64(bytes), 0, length);
        s = s.replace('/', '$');
        s.replace('"', '$');
        return s;

    }

}
