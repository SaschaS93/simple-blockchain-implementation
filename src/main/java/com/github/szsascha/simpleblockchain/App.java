package com.github.szsascha.simpleblockchain;

import com.github.szsascha.simpleblockchain.chain.Chain;
import com.github.szsascha.simpleblockchain.chain.ChainBuilder;
import com.github.szsascha.simpleblockchain.consensus.Consensus;
import com.github.szsascha.simpleblockchain.consensus.ConsensusFactory;
import com.github.szsascha.simpleblockchain.entities.Transaction;
import com.github.szsascha.simpleblockchain.validation.Validator;
import com.github.szsascha.simpleblockchain.validation.ValidatorFactory;
import com.github.szsascha.simpleblockchain.wallet.Wallet;
import com.github.szsascha.simpleblockchain.wallet.WalletFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App 
{
    public static void main(String[] args) {
        Wallet rewardWallet = WalletFactory.createSimpleWallet();
        Wallet wallet1 = WalletFactory.createSimpleWallet();
        Wallet wallet2 = WalletFactory.createSimpleWallet();

        Consensus consensus = ConsensusFactory.createSimpleProofOfWork(rewardWallet);
        Validator validator = ValidatorFactory.createSimpleValidator();
        
        Chain chain = new ChainBuilder()
                        .consensus(consensus)
                        .validator(validator)
                        .buildSimpleChain();

        chain.createNextBlock();

        Transaction t1 = rewardWallet.transfer(wallet1.getPublicKey(), 1);
        chain.addTransaction(t1);
        chain.createNextBlock();

        Transaction t2 = wallet1.transfer(wallet2.getPublicKey(), 1);
        chain.addTransaction(t2);
        Transaction t3 = rewardWallet.transfer(wallet2.getPublicKey(), 1);
        chain.addTransaction(t3);
        chain.createNextBlock();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(chain));

        System.out.println("Balance of szsascha: " + rewardWallet.getOnChainBalance(chain));
        System.out.println("Balance of Testacc: " + wallet1.getOnChainBalance(chain));
        System.out.println("Balance of Testacc1: " + wallet2.getOnChainBalance(chain));
    }
}
