package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Person extends AbstractClient implements Comparable<Person> {
    //region ATTRIBUTES
    private int BSN;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    //endregion

    //region CONSTRUCTOR

    /**
     * Constructor for creating a customer of LeenmanBank containing login in, personal and contact details.
     *
     * @param username            Username to log in on the online account
     * @param password            Password to log in on the online account
     * @param email               Email address on which the account/customer are registered
     * @param street              Street name of customer
     * @param houseNumber         House number of customer
     * @param houseNumberAddition House number addition of customer
     * @param zipCode             Zip code of customer
     * @param city                City of customer
     * @param BSN                 Social security number of customer
     * @param firstName           First name of customer
     * @param lastName            Last name of customer
     * @param dateOfBirth         Date of birth of customer
     * @should Create a valid person with all above details
     * @should Check if the username is unique
     * @should Check if the password has at least 2 characters
     * @should Throw illegal argument exception if the password has less than 2 characters
     * @should Not create the Person if the password has less than 2 characters
     * @should Check if the person is already registered (same BSN) and not create a new instance of Person when so
     *          but return existing one
     */
    public Person(String username, String password, String email, String street, int houseNumber, String houseNumberAddition, String zipCode, String city, int BSN, String firstName, String lastName, Date dateOfBirth) {
        super(username, password, email, street, houseNumber, houseNumberAddition, zipCode, city);
        // Check if username exists, if so, return existing Person
        if (getAllBSN().contains(BSN)) {
            System.out.println("Deze persoon bestaat al.");
            //TODO
        } else {
            // If BSN is unique, create new Person
            this.BSN = BSN;
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = dateOfBirth;
            Account account = new Account(this, AccountType.CONSUMER);
        }
    }

    //endregion

    //region METHODS

    //endregion

    //region Helper Methods (private)
    private List<Integer> getAllBSN() {
        List<Integer> numbers = new ArrayList<>();

        // Get all BSN from DAO/Repo
//        for (Integer bsn : ?? ){
//            numbers.add(bsn);
//        }
        //TODO

        return numbers;
    }
    //endregion

    //region GETTERS & SETTERS
    public int getBSN() {
        return BSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return super.toString() + "Person{" +
                "BSN=" + BSN +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return BSN == person.BSN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BSN);
    }

    @Override
    public int compareTo(Person p) {
        return this.BSN - p.getBSN();
    }

//endregion
}
