package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    //region FINAL ATTRIBUTES
    private final String VALID_USERNAME = "olifa~ntje123";
    private final String VALID_PASSWORD = "FonTysROCKS!";
    private final String VALID_EMAIL = "ikbeneenstudent@student.fontys.nl";
    private final String VALID_STREET = "Dorpstraat";
    private final int VALID_HOUSE_NUMBER = 123;
    private final String VALID_HOUSE_NUMBER_ADDITION = "B-3c";
    private final String VALID_ZIPCODE = "1234AZ";
    private final String VALID_CITY = "Eindhoven";
    private final int VALID_BSN = 123456789;
    private final String VALID_FIRST_NAME = "Freddy";
    private final String VALID_LAST_NAME = "Von Thunder";
    private final Date VALID_DATE_OF_BIRTH = new Date(1970, 5, 1); //TODO: review and use Calender instead?
    private final String STRING_EXISTING_USERNAME = "Deze gebruikersnaam bestaat al, kies een andere.";

    //endregion

    /**
     * @verifies if all attributes of a person are correctly saved
     * @see Person#Person(String, String, String, String, int, String, String, String, int, String, String, Date)
     */
    @Test
    public void constructor_createValidPersonWithAllAttributes() {
        // Arrange

        // Act
        Person testConsumer = new Person(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);

        // Assert
        assertEquals(VALID_USERNAME, testConsumer.getUsername());
        assertEquals(VALID_PASSWORD, testConsumer.getPassword());
        assertEquals(VALID_EMAIL, testConsumer.getEmail());
        assertEquals(VALID_STREET, testConsumer.getStreet());
        assertEquals(VALID_HOUSE_NUMBER, testConsumer.getHouseNumber());
        assertEquals(VALID_HOUSE_NUMBER_ADDITION, testConsumer.getHouseNumberAddition());
        assertEquals(VALID_ZIPCODE, testConsumer.getZipCode());
        assertEquals(VALID_CITY, testConsumer.getCity());
        assertEquals(VALID_BSN, testConsumer.getBSN());
        assertEquals(VALID_FIRST_NAME, testConsumer.getFirstName());
        assertEquals(VALID_LAST_NAME, testConsumer.getLastName());
        assertEquals(VALID_DATE_OF_BIRTH, testConsumer.getDateOfBirth());
    }

    /**
     * @verifies if the entered username is unique, i.o.w. no other AbstractClient has the same username
     * @see Person#Person(String, String, String, String, int, String, String, String, int, String, String, Date)
     */
    @Test
    public void constructor_checkIfUserNameIsUniqueAndThrowIllegalArgumentExceptionIfNot() throws IllegalArgumentException {
        // Arrange
        final String SECOND_VALID_USERNAME = "jantjuh2022";
        final String THIRD_VALID_USERNAME = "erikOpVrijdag@MIW!";

        // Act
        Person testConsumer1 = new Person(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);
        Person testConsumer2 = new Person(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);
        Person testConsumer3 = new Person(SECOND_VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);
        Person testConsumer4 = new Person(THIRD_VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);
        Person testConsumer5 = new Person(SECOND_VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);

        // Assert
        assertEquals(VALID_USERNAME, testConsumer1.getUsername());
        assertEquals(SECOND_VALID_USERNAME, testConsumer3.getUsername());
        assertEquals(THIRD_VALID_USERNAME, testConsumer4.getUsername());

        assertNull(testConsumer2, STRING_EXISTING_USERNAME);
        assertNull(testConsumer5, STRING_EXISTING_USERNAME);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Person lambdaTestConsumer = new Person(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);
        });
    }

    /**
     * @verifies if all passwaord requirements are met:
     * - at least 2 characters long
     * @see Person#Person(String, String, String, String, int, String, String, String, int, String, String, Date)
     */
    @ParameterizedTest
    @MethodSource("validPasswords")
    public void constructor_checkPasswordRequirements(String password) {
        // Act
        Person testConsumer = new Person(VALID_USERNAME, password, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);

        // Assert
        assertEquals(password, testConsumer.getPassword());
    }

    /**
     * @verifies if an IllegalArgumentException is thrown if one of the password requirements is not met
     * @see Person#Person(String, String, String, String, int, String, String, String, int, String, String, Date)
     */
    @ParameterizedTest
    @MethodSource("invalidPasswords")
    public void constructor_shouldThrowInvalidArgumentExceptionWhenPasswordRequirementsAreNotMet(String password) throws IllegalArgumentException {
        // Arrange, Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Person lambdaTestConsumer = new Person(VALID_USERNAME, password, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);
        });
    }

    /**
     * @verifies that the person object is not created if one of the password requirements is not met
     * @see Person#Person(String, String, String, String, int, String, String, String, int, String, String, Date)
     */
    @ParameterizedTest
    @MethodSource("invalidPasswords")
    public void constructor_shouldNotCreatePersonWhenPasswordRequirementsAreNotMet(String password) throws IllegalArgumentException {
        // Arrange
        Person testConsumer;

        // Act
        testConsumer = new Person(VALID_USERNAME, password, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);

        // Assert
        assertNull(testConsumer,STRING_EXISTING_USERNAME);
    }

    /**
     * @verifies if the person is already registered (the BSN number is already in use) and use the existing Person
     * instead of creating a new Person object
     * @see Person#Person(String, String, String, String, int, String, String, String, int, String, String, Date)
     */
    @Test
    public void constructor_checkIfBSNExistsAndReturnExistingPerson() {
        // Arrange

        // Act
        Person testConsumer = new Person(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL, VALID_STREET, VALID_HOUSE_NUMBER, VALID_HOUSE_NUMBER_ADDITION, VALID_ZIPCODE, VALID_CITY, VALID_BSN, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_DATE_OF_BIRTH);

        // Assert
        fail();
        //TODO
    }


    //region Helper Methods
    private static Stream<String> validPasswords() {
        return Stream.of(
                "123", "abc", "Ab", "1a2b", "*/", "-1", "abcdefghijklmnopqrstuvwxyz0123456789"
        );
    }

    private static Stream<String> invalidPasswords() {
        return Stream.of(
                "1", "a", "A", "*", "$"
        );
    }

    //endregion
}
