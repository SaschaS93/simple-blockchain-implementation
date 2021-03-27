package com.github.saschas93.simpleblockchain.entities;

import java.sql.Timestamp;
import java.util.List;

public interface Block {

    public String createHash();

    public long getIndex();

    public Timestamp getTimestamp();

    public String getPreviousHash();

    public String getHash();

    public List<Transaction> getTransactions();

    public Object getData();

}
