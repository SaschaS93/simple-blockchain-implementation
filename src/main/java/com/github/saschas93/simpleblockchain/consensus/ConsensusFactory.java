package com.github.saschas93.simpleblockchain.consensus;

import com.github.saschas93.simpleblockchain.wallet.Wallet;

public class ConsensusFactory {
    
    public static Consensus createSimpleProofOfWork(Wallet rewardWallet) {
        return new SimpleProofOfWork(rewardWallet);
    }

}
