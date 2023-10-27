package com.cs3343.demo.core;

public enum CookStatus{
    READY("ready"),
    BUSY("busy");


    private String status;


    private CookStatus(String status) {
        this.status = status;

    }

    public String toString() {
        return status;
    }

}