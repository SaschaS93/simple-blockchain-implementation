package com.github.saschas93.simpleblockchain.chain;

import com.github.saschas93.simpleblockchain.consensus.Consensus;
import com.github.saschas93.simpleblockchain.validation.Validator;

public class ChainBuilder {
    
    private Consensus consensus;

    private Validator validator;

    public ChainBuilder consensus(Consensus consensus) {
        this.consensus = consensus;
        return this;
    }

    public ChainBuilder validator(Validator validator) {
        this.validator = validator;
        return this;
    }

    public Chain buildSimpleChain() {
        return new SimpleChain(consensus, validator);
    }

}
