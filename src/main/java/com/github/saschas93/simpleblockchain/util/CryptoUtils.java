package com.github.saschas93.simpleblockchain.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CryptoUtils {
    
    public static String bytesToBase64String(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] bytesFromBase64String(String string) {
        return Base64.getDecoder().decode(string);
    }

    public static PrivateKey restorePrivateKeyFromBase64String(String base64) throws Exception {
        byte[] privateBytes = bytesFromBase64String(base64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
        KeyFactory keyFactoryPrivate = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactoryPrivate.generatePrivate(keySpec);
        return privateKey;
    }

    public static PublicKey restorePublicKeyFromBase64String(String base64) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(base64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey; 
    }

    public static String signString(String base64PrivateKey, String stringToSign) throws Exception{
        PrivateKey privateKey = restorePrivateKeyFromBase64String(base64PrivateKey);

        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(privateKey);
        ecdsa.update(stringToSign.getBytes());
        byte[] signatureBytes = ecdsa.sign();

        return bytesToBase64String(signatureBytes);
    }

    public static boolean isSignatureValid(String signature, String base64PublicKey, String signedString) throws Exception{
        PublicKey publicKey = restorePublicKeyFromBase64String(base64PublicKey);
        
        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initVerify(publicKey);
        ecdsa.update(signedString.getBytes());
        return ecdsa.verify(bytesFromBase64String(signature));
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());
        return keyGen.generateKeyPair();
    }

}
