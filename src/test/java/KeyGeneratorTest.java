import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */

/**
 * 
 * @author su.zhang
 * @version $Id: KeyPairGenerator.java, v 0.1 2012-9-6 上午11:28:11 su.zhang Exp $
 */
public class KeyGeneratorTest {
    
    public static void main(String[] args) {try {
        // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(1024);
        KeyPair keypair = keyGen.genKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();
        System.out.println("public:"+publicKey);
        System.out.println("private:"+privateKey);

        // Generate a 576-bit DH key pair
        keyGen = KeyPairGenerator.getInstance("DH");
        keyGen.initialize(576);
        keypair = keyGen.genKeyPair();
        privateKey = keypair.getPrivate();
        publicKey = keypair.getPublic();
        System.out.println("public:"+publicKey);
        System.out.println("private:"+privateKey);
        // Generate a 1024-bit RSA key pair
        keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        keypair = keyGen.genKeyPair();
        privateKey = keypair.getPrivate();
        publicKey = keypair.getPublic();
        System.out.println("public:"+publicKey);
        System.out.println("private:"+privateKey);
    } catch (java.security.NoSuchAlgorithmException e) {
    }}

}
