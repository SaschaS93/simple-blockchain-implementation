package com.github.szsascha.simpleblockchain.wallet;

import com.github.szsascha.simpleblockchain.chain.Chain;
import com.github.szsascha.simpleblockchain.entities.Transaction;

public interface Wallet {
    
    public String getPublicKey();

    public Transaction transfer(String toAddress, long amount);

    public long getOnChainBalance(Chain chain);

}
