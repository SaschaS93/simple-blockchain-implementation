package com.github.szsascha.simpleblockchain.validation;

import com.github.szsascha.simpleblockchain.chain.Chain;
import com.github.szsascha.simpleblockchain.entities.Block;
import com.github.szsascha.simpleblockchain.entities.Transaction;

public interface Validator {
    
    public boolean isChainValid(Chain chain);

    public boolean isBlockValid(Block block, Chain chain);

    public boolean isTransactionValid(Transaction transaction, Chain chain);

}
