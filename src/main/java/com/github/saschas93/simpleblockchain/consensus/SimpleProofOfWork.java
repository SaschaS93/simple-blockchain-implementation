package com.github.saschas93.simpleblockchain.consensus;

import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.util.HashUtils;

class SimpleProofOfWork implements Consensus {

    private long difficulty;

    public SimpleProofOfWork() {
        this.difficulty = 3;
    }

    public void onBlockCreation(Block block) {
        String prefix = "";
        for (int i = 0; i < difficulty; i++) {
            prefix += "0";
        }

        String hash = "";
        long nonce = -1;
        while(!hash.startsWith(prefix)) {
            nonce++;
            hash = HashUtils.stringToSha256String(block.getHash() + nonce);
        }
    }

}
