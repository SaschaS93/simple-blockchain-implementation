package com.github.saschas93.simpleblockchain.chain;

import java.util.ArrayList;
import java.util.List;

import com.github.saschas93.simpleblockchain.consensus.Consensus;
import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.BlockBuilder;
import com.github.saschas93.simpleblockchain.entities.Transaction;
import com.github.saschas93.simpleblockchain.util.TimestampUtil;
import com.github.saschas93.simpleblockchain.validation.Validator;

class SimpleChain implements Chain {

    private List<Block> chain = new ArrayList<Block>();

    private List<Transaction> pendingTransactions = new ArrayList<Transaction>();

    private Consensus consensus;

    private Validator validator;

    SimpleChain(Consensus consensus, Validator validator) {
        this.consensus = consensus;
        this.validator = validator;
    }

    @Override
    public List<Block> getFullChain() {
        return this.chain;
    }

    @Override
    public List<Transaction> getPendingTransactions() {
        return this.pendingTransactions;
    }

    @Override
    public void createNextBlock() {
        String previousHash = "";

        if (this.chain.size() > 0) {
            Block previousBlock = this.chain.get(this.chain.size() - 1);
            previousHash = previousBlock.getHash();
        }

        // Add reward transaction for genesis
        if (this.pendingTransactions.size() < 1) this.generateAndAddRewardTransaction();

        Block block = new BlockBuilder()
                        .index(this.chain.size())
                        .timestamp(TimestampUtil.getNow())
                        .previousHash(previousHash)
                        .data(null)
                        .addTransactions(this.pendingTransactions)
                        .buildSimpleBlock();

        this.consensus.onBlockCreation(block);

        this.addBlock(block);       
    }

    @Override
    public void addBlock(Block block) {
        if (!this.validator.isBlockValid(block, this)) {
            // Discard block
            return;
        }

        this.chain.add(block);

        // Remove and discard block if chain is invalid after adding
        if (!this.validator.isChainValid(this)) {
            this.chain.remove(block);
            return;
        }

        // Remove added transactions from pending transactions
        for (Transaction transaction : block.getTransactions()) {
            this.pendingTransactions.remove(transaction);
        }
        
        this.generateAndAddRewardTransaction();
    }

    private void generateAndAddRewardTransaction() {
        Transaction transaction = this.consensus.createRewardTransaction();
            
        // Don't validate reward transaction, just add it without validation because there is no real sender
        this.pendingTransactions.add(transaction);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (!this.validator.isTransactionValid(transaction, this)) {
            // Discard transaction
            return;
        }
        this.pendingTransactions.add(transaction);        
    }

    @Override
    public Consensus getConsensus() {
        return this.consensus;
    }

    @Override
    public Validator getValidator() {
        return this.validator;
    }
    
}
