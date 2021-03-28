package com.github.saschas93.simpleblockchain.wallet;

import java.security.KeyPair;

import com.github.saschas93.simpleblockchain.entities.Transaction;
import com.github.saschas93.simpleblockchain.entities.TransactionBuilder;
import com.github.saschas93.simpleblockchain.util.CryptoUtils;

class SimpleWallet implements Wallet {

    private String publicKey;

    private String privateKey;
    
    SimpleWallet() {
        // Create new wallet
        try {
            KeyPair keyPair = CryptoUtils.generateKeyPair();
            this.publicKey = CryptoUtils.bytesToBase64String(keyPair.getPublic().getEncoded());
            this.privateKey = CryptoUtils.bytesToBase64String(keyPair.getPrivate().getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    SimpleWallet(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public Transaction transfer(String toAddress, long amount) {
        Transaction transaction = new TransactionBuilder()
                                    .fromAddress(this.publicKey)
                                    .toAddress(toAddress)
                                    .amount(amount)
                                    .buildSimpleTransaction();
        transaction.sign(this.privateKey);
        return transaction;
    }

    @Override
    public String getPublicKey() {
        return this.publicKey;
    }
}
