package com.github.szsascha.simpleblockchain.chain;

import java.util.List;

import com.github.szsascha.simpleblockchain.consensus.Consensus;
import com.github.szsascha.simpleblockchain.entities.Block;
import com.github.szsascha.simpleblockchain.entities.Transaction;
import com.github.szsascha.simpleblockchain.validation.Validator;

public interface Chain {
    
    public List<Block> getFullChain();

    public List<Transaction> getPendingTransactions();

    public void createNextBlock();

    public void addBlock(Block block);

    public void addTransaction(Transaction transaction);

    public Consensus getConsensus();

    public Validator getValidator();
}
