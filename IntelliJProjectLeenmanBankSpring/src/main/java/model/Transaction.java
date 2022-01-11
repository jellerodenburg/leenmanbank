package model;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    //region ATTRIBUTES
    int id;
    // withdraw
    Account debitAccount;
    // deposit
    Account creditAccount;
    double amount;
    String description;
    Date date;
    //endregion

    //region CONSTRUCTOR
    public Transaction(int id, Account debitAccount, Account creditAccount, double amount, String description, Date date) {
        this.id = id;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
    //endregion

    //region METHODS


    //endregion

    //region GETTERS & SETTERS

    public Account getDebitAccount() {
        return debitAccount;
    }

    public double getAmount() {
        return amount;
    }

    public Account getCreditAccount() {
        return creditAccount;


    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //endregion
}
