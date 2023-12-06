package com.cs3343.demo.core;

public class ExceptionHandling {
}

class ExceptionUnknownCommand extends Exception{
    public ExceptionUnknownCommand(){
        super("Unknown Command, please use the approved commands.");
    }

    public ExceptionUnknownCommand(String msg){
        super(msg);
    }
}

class ExceptionInsufficientCommand extends Exception{
    public ExceptionInsufficientCommand(){
        super("Insufficient Command, please provide enough information.");
    }

    public ExceptionInsufficientCommand(String msg){
        super(msg);
    }
}

class ExceptionInvalidDate extends Exception{
    public ExceptionInvalidDate(){
        super("");
    }

    public ExceptionInvalidDate(String msg){
        super(msg);
    }
}

