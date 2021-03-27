package com.github.saschas93.simpleblockchain.consensus;

public class ConsensusFactory {
    
    public static Consensus createSimpleProofOfWork() {
        return new SimpleProofOfWork();
    }

}
