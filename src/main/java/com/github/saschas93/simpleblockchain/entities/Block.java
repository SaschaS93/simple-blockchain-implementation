package com.github.saschas93.simpleblockchain.entities;

import java.sql.Timestamp;

public interface Block {

    public String createHash();

    public long getIndex();

    public Timestamp getTimestamp();

    public String getPreviousHash();

    public String getHash();

    public Object getData();

}
