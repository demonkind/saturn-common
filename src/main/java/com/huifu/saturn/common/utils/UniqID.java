/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author su.zhang
 * @version $Id: UniqID.java, v 0.1 2012-9-6 下午02:53:03 su.zhang Exp $
 */
public class UniqID {
    

    private static final Log log = LogFactory.getLog(UniqID.class);
    private static char[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'
        };
    private static UniqID me = new UniqID();
    private String hostAddr;
    private Random random = new SecureRandom();
    private MessageDigest mHasher;
    private UniqTimer timer = new UniqTimer();

    private UniqID() {
        try {
            InetAddress addr = InetAddress.getLocalHost();

            hostAddr = addr.getHostAddress();
        } catch (IOException e) {
            log.error("[UniqID] Get HostAddr Error", e);
            hostAddr = String.valueOf(System.currentTimeMillis());
        }

        if (StringUtils.isBlank(hostAddr) || "127.0.0.1".equals(hostAddr)) {
            hostAddr = String.valueOf(System.currentTimeMillis());
        }

        if (log.isDebugEnabled()) {
            log.debug("[UniqID]hostAddr is:" + hostAddr);
        }

        try {
            mHasher = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nex) {
            mHasher = null;
            log.error("[UniqID]new MD% Hasher error", nex);
        }
    }

    public static UniqID getInstance() {
        return me;
    }

    public String getUniqID() {
        StringBuffer sb = new StringBuffer();
        long t = timer.getCurrentTime();

        sb.append(t);

        sb.append("-");

        sb.append(random.nextInt(8999) + 1000);

        sb.append("-");
        sb.append(hostAddr);

        sb.append("-");
        sb.append(Thread.currentThread().hashCode());

        if (log.isDebugEnabled()) {
            log.debug("[UniqID.getUniqID]" + sb.toString());
        }

        return sb.toString();
    }

    public String getUniqIDHash() {
        String id = getUniqID();

        if (mHasher != null) {
            synchronized (mHasher) {
                byte[] bt = mHasher.digest(id.getBytes());
                int l = bt.length;

                char[] out = new char[l << 1];

                for (int i = 0, j = 0; i < l; i++) {
                    out[j++] = digits[(0xF0 & bt[i]) >>> 4];
                    out[j++] = digits[0x0F & bt[i]];
                }

                if (log.isDebugEnabled()) {
                    log.debug("[UniqID.getuniqIDHash]" + (new String(out)));
                }

                return new String(out);
            }
        } else {
            return id;
        }
    }

    private class UniqTimer {
        private long lastTime = System.currentTimeMillis();

        public synchronized long getCurrentTime() {
            long currTime = System.currentTimeMillis();

            lastTime = Math.max(lastTime + 1, currTime);

            return lastTime;
        }
    }


}
