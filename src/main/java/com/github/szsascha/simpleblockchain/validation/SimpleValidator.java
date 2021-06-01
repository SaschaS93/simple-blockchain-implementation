package com.github.szsascha.simpleblockchain.validation;

import com.github.szsascha.simpleblockchain.chain.Chain;
import com.github.szsascha.simpleblockchain.entities.Block;
import com.github.szsascha.simpleblockchain.entities.Transaction;
import com.github.szsascha.simpleblockchain.util.CryptoUtils;
import com.github.szsascha.simpleblockchain.wallet.WalletFactory;

class SimpleValidator implements Validator {

    @Override
    public boolean isChainValid(Chain chain) {
        String previousHash = "";
        for (Block block : chain.getFullChain()) {
            if (!previousHash.equals(block.getPreviousHash())) {
                return false;
            }
            previousHash = block.getHash();
        }
        return true;
    }

    @Override
    public boolean isBlockValid(Block block, Chain chain) {
        // TODO: create block validation
        return true;
    }

    @Override
    public boolean isTransactionValid(Transaction transaction, Chain chain) {
        if (transaction.getAmount() < 1) {
            return false;
        }

        long balance = WalletFactory.lookupSimpleWallet(transaction.getFromAddress()).getOnChainBalance(chain);

        if(transaction.getAmount() < balance) return false;

        try {
            return CryptoUtils.isSignatureValid(transaction.getSignature(), transaction.getFromAddress(), transaction.getHash());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
}
