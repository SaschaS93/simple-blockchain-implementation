package com.github.szsascha.simpleblockchain.entities;

import java.sql.Timestamp;
import java.util.List;

import com.github.szsascha.simpleblockchain.util.HashUtils;

class SimpleBlock implements Block {

    private long index;

    private Timestamp timestamp;

    private String previousHash;

    private String hash;

    private Object data;

    private List<Transaction> transactions;

    SimpleBlock(long index, Timestamp timestamp, String previousHash, Object data, List<Transaction> transactions) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.data = data;
        this.transactions = transactions;
        this.hash = this.createHash();
    }

    @Override
    public String createHash() {
        return HashUtils.stringToSha256String(
            this.index 
            + ':' + this.timestamp.toString() 
            + ':' + this.previousHash 
            + ':' + HashUtils.objectToSha256String(this.data)
            + ':' + HashUtils.objectToSha256String(this.transactions)
        );
    }

    @Override
    public long getIndex() {
        return this.index;
    }

    @Override
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String getPreviousHash() {
        return this.previousHash;
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }
    
}
