package com.opentext.lambda.demo;

 public interface Defaulable {


     // Interfaces now allow default methods, the implementer may or may not implement (override) them.
     public default String notRequired() {
         return "Default implementation";
     }
}