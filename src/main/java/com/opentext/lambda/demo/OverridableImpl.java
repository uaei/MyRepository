package com.opentext.lambda.demo;

public class OverridableImpl implements Defaulable {

    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}