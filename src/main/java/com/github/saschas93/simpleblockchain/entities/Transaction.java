package com.github.saschas93.simpleblockchain.entities;

public interface Transaction {
    
    public String getFromAddress();

    public String getToAddress();

    public long getAmount();

}
