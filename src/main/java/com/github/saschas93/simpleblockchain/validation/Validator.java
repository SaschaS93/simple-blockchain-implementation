package com.github.saschas93.simpleblockchain.validation;

import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.Transaction;

public interface Validator {
    
    public boolean isBlockValid(Block block);

    public boolean isTransactionValid(Transaction transaction);

}
