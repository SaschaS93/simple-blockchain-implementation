package com.github.saschas93.simpleblockchain.wallet;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

import com.github.saschas93.simpleblockchain.entities.Transaction;
import com.github.saschas93.simpleblockchain.entities.TransactionBuilder;
import com.github.saschas93.simpleblockchain.util.HashUtils;

class SimpleWallet implements Wallet {

    private String publicKey;

    private String privateKey;
    
    SimpleWallet() {
        // Create new wallet
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");

            keyGen.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());

            KeyPair keyPair = keyGen.generateKeyPair();
            this.publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            this.privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
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
