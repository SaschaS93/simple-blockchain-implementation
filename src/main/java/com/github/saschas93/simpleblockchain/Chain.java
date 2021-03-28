package com.github.saschas93.simpleblockchain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.saschas93.simpleblockchain.consensus.Consensus;
import com.github.saschas93.simpleblockchain.consensus.ConsensusFactory;
import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.BlockBuilder;
import com.github.saschas93.simpleblockchain.entities.Transaction;
import com.github.saschas93.simpleblockchain.entities.TransactionBuilder;
import com.github.saschas93.simpleblockchain.validation.Validator;
import com.github.saschas93.simpleblockchain.validation.ValidatorFactory;
import com.github.saschas93.simpleblockchain.wallet.Wallet;
import com.github.saschas93.simpleblockchain.wallet.WalletFactory;

public class Chain {

    private final static long REWARD = 1;

    private Wallet wallet;

    private List<Block> chain = new ArrayList<Block>();

    private List<Transaction> pendingTransactions = new ArrayList<Transaction>();

    private Consensus consensus = ConsensusFactory.createSimpleProofOfWork();

    private Validator validator = ValidatorFactory.createSimpleValidator();

    public Chain() {
        // TODO: Wallet created dynamically for testing here
        this.wallet = WalletFactory.createSimpleWallet();
    }

    public void addBlock(String data) {
        String previousHash = "";

        if (this.chain.size() > 0) {
            Block previousBlock = this.chain.get(this.chain.size() - 1);
            previousHash = previousBlock.getHash();
        }

        this.addMiningRewardTransaction();

        Block newBlock = new BlockBuilder()
                        .index(chain.size())
                        .timestamp(new Timestamp(new Date().getTime()))
                        .previousHash(previousHash)
                        .data(data)
                        .addTransactions(this.pendingTransactions)
                        .buildSimpleBlock();

        consensus.onBlockCreation(newBlock);

        if (this.validator.isBlockValid(newBlock)) this.chain.add(newBlock);

        this.pendingTransactions.clear();
    }

    public void addTransaction(Transaction transaction) {
        if (this.getBalance(transaction.getFromAddress()) < transaction.getAmount() || !this.validator.isTransactionValid(transaction)) {
            // Discard transaction
            return;
        }

        this.addMiningRewardTransaction();
        this.pendingTransactions.add(transaction);
    }

    private void addMiningRewardTransaction() {
        if (this.pendingTransactions.size() <= 0) {
            this.pendingTransactions.add(new TransactionBuilder()
                                            .fromAddress("")
                                            .toAddress(this.wallet.getPublicKey())
                                            .amount(REWARD)
                                            .buildSimpleTransaction()
                                        );
        }
    }

    public long getBalance(String address) {
        long balance = 0;

        for (Block block : this.chain) {
            for (Transaction transaction : block.getTransactions()) {
                if (transaction.getFromAddress().equals(address)) {
                    balance -= transaction.getAmount();
                }
                if (transaction.getToAddress().equals(address)) {
                    balance += transaction.getAmount();
                }
            }
        }

        return balance;
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

    public Wallet getWallet() {
        return wallet;
    }

}
