package com.wood.FactoryDefense.terminal;

public class Log {
    String message;
    String Type;
    public Log(String Type,Object message) {
        this.message = message.toString();
        this.Type = Type;
    }
    public void debugOutput(){
        System.out.println("["+Type+"]"+message);
    }
}
