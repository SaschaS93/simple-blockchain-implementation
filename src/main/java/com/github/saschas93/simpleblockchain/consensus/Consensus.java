package com.github.saschas93.simpleblockchain.consensus;

import com.github.saschas93.simpleblockchain.entities.Block;

public interface Consensus {
    
    public void onBlockCreation(Block block);

}
