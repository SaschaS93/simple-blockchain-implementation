package com.github.saschas93.simpleblockchain.entities;

class SimpleTransaction implements Transaction {

    private String fromAddress;

    private String toAddress;

    private long amount;

    SimpleTransaction(String fromAddress, String toAddress, long amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
    }

    @Override
    public String getFromAddress() {
        return this.fromAddress;
    }

    @Override
    public String getToAddress() {
        return this.toAddress;
    }

    @Override
    public long getAmount() {
        return this.amount;
    }
    
}
