package com.example.wupproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {

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
    private AccountDetails accountDetails;
    private String status;
    private String cardImage;

    protected Card(Parcel in) {
        cardId = in.readInt();
        issuer = in.readString();
        cardNumber = in.readString();
        expirationDate = in.readString();
        cardHolderName = in.readString();
        friendlyName = in.readString();
        currency = in.readString();
        cvv = in.readString();
        availableBalance = in.readInt();
        currentBalance = in.readInt();
        minPayment = in.readInt();
        dueDate = in.readString();
        reservations = in.readInt();
        balanceCarriedOverFromLastStatement = in.readInt();
        spendingsSinceLastStatement = in.readInt();
        yourLastRepayment = in.readString();
        accountDetails = in.readParcelable(AccountDetails.class.getClassLoader());
        status = in.readString();
        cardImage = in.readString();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cardId);
        dest.writeString(issuer);
        dest.writeString(cardNumber);
        dest.writeString(expirationDate);
        dest.writeString(cardHolderName);
        dest.writeString(friendlyName);
        dest.writeString(currency);
        dest.writeString(cvv);
        dest.writeInt(availableBalance);
        dest.writeInt(currentBalance);
        dest.writeInt(minPayment);
        dest.writeString(dueDate);
        dest.writeInt(reservations);
        dest.writeInt(balanceCarriedOverFromLastStatement);
        dest.writeInt(spendingsSinceLastStatement);
        dest.writeString(yourLastRepayment);
        dest.writeParcelable(accountDetails, flags);
        dest.writeString(status);
        dest.writeString(cardImage);
    }

    public static class AccountDetails implements Parcelable {

        private int accountLimit;
        private String accountNumber;

        protected AccountDetails(Parcel in) {
            accountLimit = in.readInt();
            accountNumber = in.readString();
        }

        public static final Creator<AccountDetails> CREATOR = new Creator<AccountDetails>() {
            @Override
            public AccountDetails createFromParcel(Parcel in) {
                return new AccountDetails(in);
            }

            @Override
            public AccountDetails[] newArray(int size) {
                return new AccountDetails[size];
            }
        };

        public int getAccountLimit() {
            return accountLimit;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(accountLimit);
            dest.writeString(accountNumber);
        }
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

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public String getStatus() {
        return status;
    }

    public String getCardImage() {
        return cardImage;
    }

    public static Creator<Card> getCREATOR() {
        return CREATOR;
    }
}
