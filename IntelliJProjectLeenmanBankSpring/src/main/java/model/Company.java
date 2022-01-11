package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company extends AbstractClient implements Comparable<Company> {
    //region ATTRIBUTES
    private int coCNumber;
    private String name;
    private Sector sector;
    private Employee accountManager;
    private int registrationCodePinTerminal;
    //endregion

    //region CONSTRUCTOR
    public Company(String username, String password, String email, String street, int houseNumber, String houseNumberAddition, String zipCode, String city, int coCNumber, String name, Sector sector) {
        super(username, password, email, street, houseNumber, houseNumberAddition, zipCode, city);
        // Check if CoC exists, if so, return existing Company
        if (getAllcoCNumber().contains(coCNumber)) {
            System.out.println("Dit bedrijf bestaat al.");
            //TODO
        } else {
            this.coCNumber = coCNumber;
            this.name = name;
            this.sector = sector;
            Account account = new Account(this, AccountType.SMALL_BUSINESS);
        }
    }
    //endregion

    //region METHODS

    //endregion

    //region Helper Methods (private)
    private List<Integer> getAllcoCNumber() {
        List<Integer> numbers = new ArrayList<>();

        // Get all BSN from DAO/Repo
//        for (Integer coc : ?? ){
//            numbers.add(coc);
//        }
        //TODO

        return numbers;
    }
    //endregion

    //region GETTERS & SETTERS

    public int getCoCNumber() {
        return coCNumber;
    }

    public String getName() {
        return name;
    }

    public Sector getSector() {
        return sector;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return super.toString() + "Company{" +
                "CoCNumber=" + coCNumber +
                ", name='" + name + '\'' +
                ", sector=" + sector +
                ", accountManager=" + accountManager +
                ", registrationCodePinTerminal=" + registrationCodePinTerminal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return coCNumber == company.coCNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coCNumber);
    }

    @Override
    public int compareTo(Company c) {
        return this.coCNumber - c.getCoCNumber();
    }

    //endregion
}
