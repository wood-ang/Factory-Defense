package com.wood.FactoryDefense.kotlin.terminal;

public class Log {
    String message;
    String Type;
    public Log(String message, String Type) {
        this.message = message;
        this.Type = Type;
    }
    public void debugOutput(){
        System.out.println("["+Type+"]"+message);
    }
}
