package com.github.saschas93.simpleblockchain.wallet;

import com.github.saschas93.simpleblockchain.entities.Transaction;

public interface Wallet {
    
    public String getPublicKey();

    public Transaction transfer(String toAddress, long amount);

}
