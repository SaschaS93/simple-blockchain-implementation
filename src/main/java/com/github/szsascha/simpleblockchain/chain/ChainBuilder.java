package com.github.szsascha.simpleblockchain.chain;

import com.github.szsascha.simpleblockchain.consensus.Consensus;
import com.github.szsascha.simpleblockchain.validation.Validator;

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
