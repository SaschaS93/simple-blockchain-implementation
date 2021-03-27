package com.github.saschas93.simpleblockchain.entities;

public interface Transaction {
    
    public String createHash();

    public void sign(String privateKey);

    public boolean isValid();

    public String getFromAddress();

    public String getToAddress();

    public long getAmount();

    public String getHash();

    public String getSignature();

}
