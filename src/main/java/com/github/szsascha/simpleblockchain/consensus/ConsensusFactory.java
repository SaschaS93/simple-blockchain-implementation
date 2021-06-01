package com.github.szsascha.simpleblockchain.consensus;

import com.github.szsascha.simpleblockchain.wallet.Wallet;

public class ConsensusFactory {
    
    public static Consensus createSimpleProofOfWork(Wallet rewardWallet) {
        return new SimpleProofOfWork(rewardWallet);
    }

}
