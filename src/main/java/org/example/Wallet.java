package org.example;

public class Wallet {
    private double balance;
    private String currency;

    public Wallet(double initialBalance, String currency) {
        this.balance = initialBalance;
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void depositAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance += amount;
    }

    public boolean withdrawAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public void transferFunds(Wallet recipient, double amount) {
        if (!withdrawAmount(amount)) {
            throw new IllegalArgumentException("Insufficient funds for transfer");
        }
        if (!recipient.getCurrency().equalsIgnoreCase(currency)) {
            throw new IllegalArgumentException("Cannot transfer funds between different currencies");
        }
        recipient.depositAmount(amount);
    }
}