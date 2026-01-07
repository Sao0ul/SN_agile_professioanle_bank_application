package BankSystem;

import BankSystem.managerView;

public class Account {
    private String id;
    private String name;
    private int balance = 0;
    private String password = "";

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account(String id, String name, int balance) {
        this.balance = balance;
        this.id = id;
        this.name = name;
    }

    public String getID() {
        return this.id;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    public int credit(int amount) {
        this.balance += amount;
        return balance;
    }

    public void debit(int amount) {
        if (amount <= balance) {
            this.balance -= amount;
        } else {
            managerView.showErrorpuppup("Amount Exceeded Balance");
        }
    }

    public void transfertTo(Account another, int amount) {
        if (amount <= balance) {
            this.debit(amount);
            another.credit(amount);
        } else {
            managerView.showErrorpuppup("Amount Exceeded Balance");
        }
    }

    public String toString() {
        return ("Account[id=" + this.id + ",name=" + this.name + ",balance=" + this.balance + "]");
    }

}