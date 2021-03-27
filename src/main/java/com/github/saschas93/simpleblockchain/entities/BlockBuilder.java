package com.github.saschas93.simpleblockchain.entities;

import java.sql.Timestamp;

public class BlockBuilder {
    
    private long index;

    private Timestamp timestamp;

    private String previousHash;

    private Object data;

    public BlockBuilder index(long index) {
        this.index = index;
        return this;
    }

    public BlockBuilder timestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public BlockBuilder previousHash(String previousHash) {
        this.previousHash = previousHash;
        return this;
    }

    public BlockBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public Block buildSimpleBlock() {
        return new SimpleBlock(index, timestamp, previousHash, data);
    }

}
