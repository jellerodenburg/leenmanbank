package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import repositories.RepoManager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

@Data
//@Entity
//@NoArgsConstructor
public abstract class AbstractClient {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Username is unique but id field is used in DB as PK TODO: update UML
    private String username;
    private String password;
    private Set<Account> accounts;
    private Set<Account> authorizedAccounts;
    private String email;
    private String street;
    private int houseNumber;
    private String houseNumberAddition;
    private String zipCode;
    private String city;
    //endregion

    //region CONSTRUCTOR

    //TODO sig nr4 : username and password in een apparte class, en de rest als een apparte class.

    public AbstractClient(String username, String password, String email, String street, int houseNumber, String houseNumberAddition, String zipCode, String city) throws IllegalArgumentException {
        // Check if username exists, if so, throw IllegalArgumentException
        if (checkUserName(username)) {
            throw new IllegalArgumentException("Deze gebruikersnaam bestaat al, kies een andere.");
        } else {
            // If username is unique, create AbstractClient
            this.username = username;
            this.password = password;
            this.accounts = new HashSet<>();
            this.authorizedAccounts = new HashSet<>();
            this.email = email;
            this.street = street;
            this.houseNumber = houseNumber;
            this.houseNumberAddition = houseNumberAddition;
            this.zipCode = zipCode;
            this.city = city;
        }
    }


    //endregion

    //region METHODS
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void addAuthorizedAccount(Account account) {
        this.authorizedAccounts.add(account);
    }

    public boolean removeAccount(Account account) {
        return this.accounts.remove(account);
    }

    public boolean removeAuthorizedAccount(Account account) {
        return this.authorizedAccounts.remove(account);
    }
    //endregion

    //region Helper Methods (private)
    private boolean checkUserName(String username) {
        return RepoManager.getRepoManager().containsAbstractClient(username);
    }
    //endregion

    //region GETTERS & SETTERS

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getHouseNumberAddition() {
        return houseNumberAddition;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return "AbstractClient{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                ", authorizedAccounts=" + authorizedAccounts +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", houseNumberAddition=" + houseNumberAddition +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    // Abstract clients username = unique
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractClient that = (AbstractClient) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    //endregion
}
