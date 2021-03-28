package com.github.saschas93.simpleblockchain.validation;

import com.github.saschas93.simpleblockchain.entities.Block;
import com.github.saschas93.simpleblockchain.entities.Transaction;
import com.github.saschas93.simpleblockchain.util.CryptoUtils;

class SimpleValidator implements Validator {

    @Override
    public boolean isBlockValid(Block block) {
        // TODO: create block validation
        return true;
    }

    @Override
    public boolean isTransactionValid(Transaction transaction) {
        if (transaction.getAmount() < 1) {
            return false;
        }

        try {
            return CryptoUtils.isSignatureValid(transaction.getSignature(), transaction.getFromAddress(), transaction.getHash());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
}
