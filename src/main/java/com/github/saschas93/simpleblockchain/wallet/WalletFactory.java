package com.github.saschas93.simpleblockchain.wallet;

public class WalletFactory {
    
    public static Wallet createSimpleWallet() {
        return new SimpleWallet();
    }

    public static Wallet restoreSimpleWallet(String publicKey, String privateKey) {
        return new SimpleWallet(publicKey, privateKey);
    }

    public static Wallet lookupSimpleWallet(String publicKey) {
        return new SimpleWallet(publicKey);
    }

}
