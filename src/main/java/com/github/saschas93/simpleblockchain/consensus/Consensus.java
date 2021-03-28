package com.github.saschas93.simpleblockchain.consensus;

import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.Transaction;

public interface Consensus {
    
    public void onBlockCreation(Block block);

    public Transaction createRewardTransaction();

}
