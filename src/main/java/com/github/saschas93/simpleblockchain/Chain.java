package com.github.saschas93.simpleblockchain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.github.saschas93.simpleblockchain.consensus.Consensus;
import com.github.saschas93.simpleblockchain.consensus.ConsensusFactory;
import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.BlockBuilder;

public class Chain {
    
    private ArrayList<Block> chain = new ArrayList<Block>();

    private Consensus consensus = ConsensusFactory.createSimpleProofOfWork();

    public void addBlock(String data) {
        String previousHash = "";

        if (chain.size() > 0) {
            Block previousBlock = chain.get(chain.size() - 1);
            previousHash = previousBlock.getHash();
        }

        Block newBlock = new BlockBuilder()
                        .index(chain.size())
                        .timestamp(new Timestamp(new Date().getTime()))
                        .previousHash(previousHash)
                        .data(data)
                        .buildSimpleBlock();

        consensus.onBlockCreation(newBlock);

        this.chain.add(newBlock);
    }

    public boolean isValid() {
        String previousHash = "";
        for (Block block : this.chain) {
            if (!previousHash.equals(block.getPreviousHash())) {
                return false;
            }
            previousHash = block.getHash();
        }
        return true;
    }

}
