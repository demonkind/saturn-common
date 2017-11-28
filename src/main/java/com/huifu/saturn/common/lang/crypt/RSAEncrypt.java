/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.lang.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author su.zhang
 * @version $Id: RSAEncrypt.java, v 0.1 2012-9-6 下午02:37:23 su.zhang Exp $
 */
public class RSAEncrypt {
    /** 加密算法 */
    private static final String ALGORITHM   = "RSA";

    /** CIPHER算法名称 */
    private static final String CIPHER_NAME = "RSA/ECB/PKCS1Padding";

    /* 
     * 由于RSA一次只能加密128个字节（加密后也是128个字节），所以将密文按128个字节分割，分块解密，最后将解密后的字节流连接起来。
     * 
     * (non-Javadoc)
     * @see com.alipay.paygw.engine.security.encrypt.Encrypt#decrypt(java.lang.String, java.lang.String, java.lang.String, com.alipay.paygw.core.module.enums.EncryptStyle)
     */
    public String decrypt(String content, String key, String charset) throws EncryptException {
        try {
            PrivateKey prikey = KeyReader.getPrivateKeyFromPKCS8(ALGORITHM,
                new ByteArrayInputStream(key.getBytes()));

            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, prikey);

            InputStream ins = new ByteArrayInputStream(Base64.decodeBase64(content.getBytes()));
            StringBuffer cleanBuf = new StringBuffer();

            byte[] buf = new byte[128];
            int bufl;

            while ((bufl = ins.read(buf)) != -1) {
                byte[] block = null;

                if (buf.length == bufl) {
                    block = buf;
                } else {
                    block = new byte[bufl];
                    for (int i = 0; i < bufl; i++) {
                        block[i] = buf[i];
                    }
                }

                cleanBuf.append(new String(cipher.doFinal(block), charset));
            }

            return cleanBuf.toString();
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }

    /* 
     * 由于RSA一次只能加密128个字节，所以将原始数据按100个字节分割，分块加密，最后将加密后的字节流连接起来。
     * 
     * (non-Javadoc)
     * @see com.alipay.paygw.engine.security.encrypt.Encrypt#encrypt(java.lang.String, java.lang.String, java.lang.String, com.alipay.paygw.core.module.enums.EncryptStyle)
     */
    public String encrypt(String content, String key, String charset) throws EncryptException {
        try {
            PublicKey pubkey = KeyReader.getPublicKeyFromX509(ALGORITHM, new ByteArrayInputStream(
                key.getBytes()));

            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);

            InputStream inputReader = new ByteArrayInputStream(content.getBytes(charset));
            ByteArrayOutputStream writer = new ByteArrayOutputStream();

            byte[] buf = new byte[100];
            int bufl;

            while ((bufl = inputReader.read(buf)) != -1) {
                byte[] block = null;

                if (buf.length == bufl) {
                    block = buf;
                } else {
                    block = new byte[bufl];
                    for (int i = 0; i < bufl; i++) {
                        block[i] = buf[i];
                    }
                }

                writer.write(cipher.doFinal(block));
            }

            return new String(Base64.encodeBase64(writer.toByteArray()));
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }
}
