package com.github.saschas93.simpleblockchain;

import com.github.saschas93.simpleblockchain.wallet.Wallet;
import com.github.saschas93.simpleblockchain.wallet.WalletFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App 
{
    public static void main( String[] args )
    {
        Wallet wallet1 = WalletFactory.createSimpleWallet();
        Wallet wallet2 = WalletFactory.createSimpleWallet();

        Chain blockchain = new Chain();
        blockchain.addBlock("Genesis");
        blockchain.addTransaction(blockchain.getWallet().transfer(wallet1.getPublicKey(), 1));
        blockchain.addBlock("New block");
        blockchain.addTransaction(wallet1.transfer(wallet2.getPublicKey(), 1));
        blockchain.addTransaction(blockchain.getWallet().transfer(wallet2.getPublicKey(), 1));
        blockchain.addBlock("Another block");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(blockchain));

        System.out.println("Is chain valid? " + blockchain.isValid());
        System.out.println("Balance of SaschaS93: " + blockchain.getBalance(blockchain.getWallet().getPublicKey()));
        System.out.println("Balance of Testacc: " + blockchain.getBalance(wallet1.getPublicKey()));
        System.out.println("Balance of Testacc1: " + blockchain.getBalance(wallet2.getPublicKey()));
    }
}
