package com.example.wupproject.model;

public class Card {

    private int cardId;
    private String issuer;
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;
    private String friendlyName;
    private String currency;
    private String cvv;
    private int availableBalance;
    private int currentBalance;
    private int minPayment;
    private String dueDate;
    private int reservations;
    private int balanceCarriedOverFromLastStatement;
    private int spendingsSinceLastStatement;
    private String yourLastRepayment;
    private int accountLimit;
    private String accountNumber;
    private String status;
    private String cardImage;

    public Card() {
    }

    public Card(String cardHolderName, String cardImage) {
        this.cardHolderName = cardHolderName;
        this.cardImage = cardImage;
    }

    public int getCardId() {
        return cardId;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCvv() {
        return cvv;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public int getMinPayment() {
        return minPayment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getReservations() {
        return reservations;
    }

    public int getBalanceCarriedOverFromLastStatement() {
        return balanceCarriedOverFromLastStatement;
    }

    public int getSpendingsSinceLastStatement() {
        return spendingsSinceLastStatement;
    }

    public String getYourLastRepayment() {
        return yourLastRepayment;
    }

    public int getAccountLimit() {
        return accountLimit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getCardImage() {
        return cardImage;
    }
}
