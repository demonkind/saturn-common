import java.security.SignatureException;

import com.huifu.saturn.common.lang.signature.MD5Signature;

/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */

/**
 * 
 * @author su.zhang
 * @version $Id: Md5signatureTest.java, v 0.1 2012-9-6 下午01:45:50 su.zhang Exp $
 */
public class Md5signatureTest {

    /**
     * 
     * @param args
     * @throws SignatureException 
     */
    public static void main(String[] args) throws SignatureException {
        
        MD5Signature m =new MD5Signature();
       String a =  m.sign("zhangsu", "mykey", "utf-8");
       System.out.println(a);
       m.check("zhangsu", "dccb519dd08cd32790b9140a6fdd3ea3", "mykey", "utf-8");
       System.out.println();

    }

}
