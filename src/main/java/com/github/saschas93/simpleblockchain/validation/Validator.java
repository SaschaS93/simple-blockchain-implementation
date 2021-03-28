package com.github.saschas93.simpleblockchain.validation;

import com.github.saschas93.simpleblockchain.chain.Chain;
import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.Transaction;

public interface Validator {
    
    public boolean isChainValid(Chain chain);

    public boolean isBlockValid(Block block, Chain chain);

    public boolean isTransactionValid(Transaction transaction, Chain chain);

}
