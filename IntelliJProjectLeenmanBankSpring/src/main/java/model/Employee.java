package model;

import java.util.Date;

public class Employee extends Person{
    //region ATTRIBUTES
    private JobTitle jobTitle;
    //endregion

    //region CONSTRUCTOR
    public Employee(String username, String password, String email, String street, int houseNumber, String houseNumberAddition, String zipCode, String city, int BSN, String firstName, String lastName, Date dateOfBirth, JobTitle jobTitle) {
        super(username, password, email, street, houseNumber, houseNumberAddition, zipCode, city, BSN, firstName, lastName, dateOfBirth);
        this.jobTitle = jobTitle;
    }

    //endregion

    //region METHODS

    //endregion

    //region GETTERS & SETTERS

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return super.toString() + "Employee{" +
                "jobTitle=" + jobTitle +
                '}';
    }

    //endregion
}
