package com.github.saschas93.simpleblockchain.entities;

import com.github.saschas93.simpleblockchain.util.CryptoUtils;
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
            this.signature = CryptoUtils.signString(privateKeyString, this.getHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValid() {
        if (this.fromAddress == "") return true;

        try {
            return CryptoUtils.isSignatureValid(signature, this.fromAddress, this.hash);
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
