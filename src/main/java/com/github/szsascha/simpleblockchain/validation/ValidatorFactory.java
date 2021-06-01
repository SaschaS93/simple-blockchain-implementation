package com.github.szsascha.simpleblockchain.validation;

public class ValidatorFactory {
    
    public static Validator createSimpleValidator() {
        return new SimpleValidator();
    }

}
