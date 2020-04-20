package com.example.restservice;

public class Client {
    private int balance;
    private String name;
    private String password;
    Account account;
    public Client(String name, String password, Account accountNumber){
        account = accountNumber;
        this.name = name;
        this.password = password;
    }
    int getBalance(){
        return account.getBalance();
    }
    String getName(){
        return name;
    }
    String getPassword(){
        return password;
    }
    int deposit (int amount){
        if (amount >=0) {
            account.setBalance(account.getBalance()+ amount);
            return 0;
        }
        else
            return 1;
    }
    int withdraw (int amount){
        if (amount > account.getBalance())
            return 1;
        else if (amount<0)
            return 2;
        else {
            account.setBalance(account.getBalance() - amount);
            return 0;
        }
        }
    }

