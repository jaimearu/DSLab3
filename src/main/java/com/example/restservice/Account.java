package com.example.restservice;

public class Account {
    private int number;
    private int balance;
    public Account(int number){
        this.number = number;
        balance = 0;
    }
    public int getNumber(){
        return number;
    }
    public int getBalance(){
        return balance;
    }
    public void setBalance (int amount){
        balance = amount;
    }
}
