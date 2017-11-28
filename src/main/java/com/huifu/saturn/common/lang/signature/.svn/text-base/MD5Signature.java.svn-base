/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.lang.signature;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author su.zhang
 * @version $Id: MD5Signature.java, v 0.1 2012-9-6 上午10:49:00 su.zhang Exp $
 */
public class MD5Signature  extends BaseSignature {

    String doSign(String content, String privateKey, String charset) throws SignatureException {
        String tosign = (content == null ? "" : content) + privateKey;

        try {
            return DigestUtils.md5Hex(getContentBytes(tosign, charset));
        } catch (UnsupportedEncodingException e) {
            throw new SignatureException("MD5签名[content = " + content + "; charset = " + charset
                                         + "]发生异常!", e);
        }
    }

    boolean doCheck(String content, String signature, String publicKey, String charset)
                                                                                       throws SignatureException {
        String tosign = (content == null ? "" : content) + publicKey;

        try {
            String mySign = DigestUtils.md5Hex(getContentBytes(tosign, charset));

            return StringUtils.equals(mySign, signature) ? true : false;
        } catch (UnsupportedEncodingException e) {
            throw new SignatureException("MD5验证签名[content = " + content + "; charset = " + charset
                                         + "; signature = " + signature + "]发生异常!", e);
        }
    }

}
