package com.github.saschas93.simpleblockchain.entities;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import com.github.saschas93.simpleblockchain.util.HashUtils;

class SimpleTransaction implements Transaction {

    private String fromAddress;

    private String toAddress;

    private long amount;

    private String hash;

    private String signature;

    SimpleTransaction(String fromAddress, String toAddress, long amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.hash = this.createHash();
    }

    @Override
    public String createHash() {
        return HashUtils.stringToSha256String(
            this.fromAddress 
            + ':' + this.toAddress
            + ':' + this.amount 
        );
    }

    @Override
    public void sign(String privateKeyString) {
        try {
            byte[] privateBytes = Base64.getDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
            KeyFactory keyFactoryPrivate = KeyFactory.getInstance("EC");
            PrivateKey privateKey = keyFactoryPrivate.generatePrivate(keySpec);

            Signature ecdsa = Signature.getInstance("SHA256withECDSA");

            ecdsa.initSign(privateKey);
    
            byte[] strByte = this.createHash().getBytes("UTF-8");
            ecdsa.update(strByte);
    
            byte[] signatureBytes = ecdsa.sign();

            this.signature = Base64.getEncoder().encodeToString(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValid() {
        if (this.fromAddress == "") return true;

        try {
            Signature ecdsa = Signature.getInstance("SHA256withECDSA");

            byte[] publicKeyBytes = Base64.getDecoder().decode(this.fromAddress);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            ecdsa.initVerify(publicKey);
            byte[] strByte = this.hash.getBytes("UTF-8");
            ecdsa.update(strByte);

            byte[] signature = Base64.getDecoder().decode(this.signature);

            return ecdsa.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getFromAddress() {
        return this.fromAddress;
    }

    @Override
    public String getToAddress() {
        return this.toAddress;
    }

    @Override
    public long getAmount() {
        return this.amount;
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public String getSignature() {
        return this.signature;
    }
    
}
