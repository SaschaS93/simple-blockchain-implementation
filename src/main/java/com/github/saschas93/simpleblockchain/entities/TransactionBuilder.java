package com.github.saschas93.simpleblockchain.entities;

public class TransactionBuilder {
    
    private String fromAddress;

    private String toAddress;

    private long amount;

    public TransactionBuilder fromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
        return this;
    }

    public TransactionBuilder toAddress(String toAddress) {
        this.toAddress = toAddress;
        return this;
    }

    public TransactionBuilder amount(long amount) {
        this.amount = amount;
        return this;
    }

    public Transaction buildSimpleTransaction() {
        return new SimpleTransaction(this.fromAddress, this.toAddress, this.amount);
    }

}
