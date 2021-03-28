package com.github.saschas93.simpleblockchain.validation;

public class ValidatorFactory {
    
    public static Validator createSimpleValidator() {
        return new SimpleValidator();
    }

}
