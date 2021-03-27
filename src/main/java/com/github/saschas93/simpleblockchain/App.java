package com.github.saschas93.simpleblockchain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Chain blockchain = new Chain();
        blockchain.addBlock("Genesis");
        blockchain.addTransaction("SaschaS93", "Testacc", 1);
        blockchain.addBlock("New block");
        blockchain.addTransaction("Testacc", "Testacc1", 1);
        blockchain.addTransaction("SaschaS93", "Testacc1", 1);
        blockchain.addBlock("Another block");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(blockchain));

        System.out.println("Is chain valid? " + blockchain.isValid());
        System.out.println("Balance of SaschaS93: " + blockchain.getBalance("SaschaS93"));
        System.out.println("Balance of Testacc: " + blockchain.getBalance("Testacc"));
        System.out.println("Balance of Testacc1: " + blockchain.getBalance("Testacc1"));
    }
}
