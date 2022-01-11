package model;

import net.minidev.json.JSONUtil;

import java.util.*;

public class Account {
    //region ATTRIBUTES
    // changed accountNumber to string,
    // cause in the mock data the number consists out of nunmbers and letters
    private static String lastAccountNumber = "NL06LNBK1122334455";
    private final double DEFAULT_START_BALANCE = 0.0;
    private final int DEFAULT_PINCODE = 12345;
    private final int TOP10 = 10;
    private String accountNumber;
    private AbstractClient accountHolder;
    private double balance;
    private List<Transaction> transactionHistory;
    private Set<AbstractClient> authorizedClients;
    private AccountType type;
    private int pincode;
    //endregion

    //region CONSTRUCTOR
    public Account(AbstractClient accountHolder, AccountType type) {
        this.accountNumber = lastAccountNumber;
        this.accountHolder = accountHolder;
        this.balance = DEFAULT_START_BALANCE;
        this.transactionHistory = new ArrayList<>();
        this.authorizedClients = new HashSet<>();
        this.type = type;
        this.pincode = DEFAULT_PINCODE;
        accountHolder.addAccount(this);
    }
    //endregion

    //region METHODS
    public void addAuthorizedClient(AbstractClient client) {
        this.authorizedClients.add(client);
    }

    public void removeAuthorizedClient(AbstractClient client) {
        this.authorizedClients.remove(client);
    }

    // transaction from one user to another
    public boolean processTransaction(Transaction transaction) {
        // Proces debit transaction first & if it doesn't succeed, abort transaction
        boolean debit = processDebitTransaction(transaction);
        if (!debit) {
            return false;
        }
        processCreditTransaction(transaction);
        this.transactionHistory.add(transaction);
        // Return true if both transactions have succeeded
        return true;
//        else {
//            System.out.println("Transaction doesnt need to be processed by this account");
//            return false;
//        } TODO @Suze?: waar dient dit stuk code voor?
        // suze : ik heb deze code niet geschreven weet het ook niet?
        //TODO @Suze?: process transaction hoort in AccountService/TransactionService, met de groep naar kijken
        // suze : process transation code was er all, heb gewoon aan gepast. Ik was ook aan het denken, maar wist niet zeker.
    }

    // get/generate the 10 recent transaction from the transactionHistory
    public List<Transaction> get10RecentTransaction() {
        List<Transaction> recent10Transaction;
        // if length of the list is smaller than 10
        if (this.transactionHistory.size() <= TOP10) {
            recent10Transaction = this.transactionHistory;
            return recent10Transaction;
        }
        // is transaction history length is bigger than 10
        else {
            int start = transactionHistory.size() - TOP10;
            recent10Transaction = this.transactionHistory.subList(start, (transactionHistory.size() + 1));
            return recent10Transaction;
        }
    }

    //endregion

    //region Helper Methods
    // deposit money from account
    private void processCreditTransaction(Transaction transaction) {
        // Get current balance of credit account
        double balance = transaction.getCreditAccount().getBalance();

        // Add amount to account
        balance += transaction.getAmount();
        transaction.getCreditAccount().setBalance(balance);
        System.out.println("Your account balance is now € " + balance + ".");
    }

    // withdraw money from account
    private boolean processDebitTransaction(Transaction transaction) {
        // Get current balance of debit account
        double balance = transaction.getDebitAccount().getBalance();

        // Check if balance has enough money in it
        if (transaction.getAmount() <= balance) {
            balance -= transaction.getAmount(); // TODO: extract method, duplicate code with processCreditTransaction()
            transaction.getDebitAccount().setBalance(balance);
            System.out.println("Your account balance is now € " + balance + ".");
            return true;
        } else {
            System.out.println("Insignificant balance! \n Withdraw canceled");
            return false;
        }
    }

    //endregion

    //region GETTERS & SETTERS

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }

    //endregion
}
