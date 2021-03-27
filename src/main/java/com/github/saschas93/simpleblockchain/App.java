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
        blockchain.addBlock("New block");
        blockchain.addBlock("Another block");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(blockchain));

        System.out.println("Is chain valid? " + blockchain.isValid());
    }
}
