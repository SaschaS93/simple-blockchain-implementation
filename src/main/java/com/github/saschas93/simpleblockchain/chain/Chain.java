package com.github.saschas93.simpleblockchain.chain;

import java.util.List;

import com.github.saschas93.simpleblockchain.consensus.Consensus;
import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.Transaction;
import com.github.saschas93.simpleblockchain.validation.Validator;

public interface Chain {
    
    public List<Block> getFullChain();

    public List<Transaction> getPendingTransactions();

    public void createNextBlock();

    public void addBlock(Block block);

    public void addTransaction(Transaction transaction);

    public Consensus getConsensus();

    public Validator getValidator();
}
