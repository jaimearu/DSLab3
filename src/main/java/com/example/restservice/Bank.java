package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class Bank {
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Account> accounts = new ArrayList<Account>();
    int currentClient = -1;
    boolean loggedIn = false;
    public Bank(){
        accounts.add(new Account(0001));
        accounts.add(new Account(0002));
        clients.add(new Client("debby","1234",accounts.get(0)));
        clients.add(new Client("mike", "mikeiscool",accounts.get(1)));
        clients.add(new Client("wifeMike", "wifeMike",accounts.get(1)));
        clients.get(1).deposit(1000);
    }
    private void addClient(String name, String password, Account number) {
        Client c = new Client(name, password, number);
        clients.add(c);
    }
    private void removeClient(String name){
        for(Client c : clients){
            if (c.getName().equals(name))
                clients.remove(clients.indexOf(c));
        }
    }
    int logIn(String name, String password){
        for(Client c : clients) {
            if (c.getName().equals(name) && c.getPassword().equals(password)) {
                currentClient = clients.indexOf(c);
                return currentClient;
            }
        }
        return -1;
    }
    void test(){
    }
    @GetMapping("/logIn")
    public String output (@RequestParam(value = "name", defaultValue = "omo") String name,@RequestParam(value = "password", defaultValue = "omo") String password) {
        if (logIn(name, password) != -1) {
            loggedIn = true;
            return "Login succesfull, welcome " + name;
        } else return "login failed";
    }
    @GetMapping("/Deposit")
    public String output2 (@RequestParam(value = "amount", defaultValue = "omo") int amount){
        if(loggedIn){
            if(clients.get(currentClient).deposit(amount) == 0)
            return amount+" has been deposited to your account.\n your current balance is now "+clients.get(currentClient).getBalance();
            else return "Error, Can not deposit negative amount";
        }
        else
            return "not currently logged in, please log in first";
    }
    @GetMapping("/Withdraw")
    public String output3 (@RequestParam(value = "amount", defaultValue = "omo") int amount) {
        if (loggedIn) {
            if (clients.get(currentClient).withdraw(amount) == 0) {
                return "Succesfully withdrawn €" + amount + "\n your current balance is now " + clients.get(currentClient).getBalance();
            } else if (clients.get(currentClient).withdraw(amount) == 1) {
                return "Error, Amount withdrawn is more than current balance.";
            } else if (clients.get(currentClient).withdraw(amount) == 2) {
                return "Error, Cannot withdraw negative amount.";
            } else return "Error while withdrawing";

        }
        else
            return "not currently logged in, please log in first";
    }
    @GetMapping("/Balance")
    public String output4 () {
        if (loggedIn) {
            return "Your current balance equals: €"+clients.get(currentClient).getBalance();
        }
        else
            return "not currently logged in, please log in first";
    }
    @GetMapping("/logOut")
    public String output5 () {
            if (loggedIn) {
                loggedIn = false;
                return clients.get(currentClient).getName()+" has sucessfully logged out.";
            }
            else
                return "not currently logged in, please log in first";
        }

}
