package com.github.szsascha.simpleblockchain.consensus;

import com.github.szsascha.simpleblockchain.entities.Block;
import com.github.szsascha.simpleblockchain.entities.Transaction;

public interface Consensus {
    
    public void onBlockCreation(Block block);

    public Transaction createRewardTransaction();

}
