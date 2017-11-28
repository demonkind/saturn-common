/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.lang.signature;

import java.security.SignatureException;

/**
 * 
 * @author su.zhang
 * @version $Id: Signature.java, v 0.1 2012-9-6 上午10:46:21 su.zhang Exp $
 */
public interface Signature {

    /**
     * 使用privateKey对原始数据进行签名
     * 
     * @param content 原始数据
     * @param privateKey 私钥
     * @param charset 编码集
     * @return 签名数据
     */
    public String sign(String content, String privateKey, String charset) throws SignatureException;

    /**
     * 验证签名
     * 
     * @param content 原始数据
     * @param signature 签名数据
     * @param publicKey 公钥
     * @param charset 编码集
     * @return True 签名验证通过 False 签名验证失败
     */
    public boolean check(String content, String signature, String publicKey, String charset)
                                                                                            throws SignatureException;
}
