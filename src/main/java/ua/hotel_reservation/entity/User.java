package ua.hotel_reservation.entity;


import jakarta.persistence.*;
import ua.hotel_reservation.exception.enitiy_exceptions.WrongAgeException;
import ua.hotel_reservation.exception.enitiy_exceptions.WrongPhoneNumberException;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "phone_number")
    private String phoneNumber;

    public int getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    //setter for age that checks whether age between 0 and 150 values
    public void setAge(int age) {
        if (ageChecker(age)) {
            throw new WrongAgeException();
        }
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    //setter for phone number that checks whether the phone number matches the pattern
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumberPatternChecker(phoneNumber)){
            throw new WrongPhoneNumberException();
        }
        this.phoneNumber = phoneNumber;
    }

    //checker for phone number pattern
    public static  boolean phoneNumberPatternChecker(String phoneNumber){
         return !phoneNumber.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$\n");
    }

    //checker for age
    public static boolean ageChecker(int age){
        return age < 0 || age > 150;
    }

    public User(){

    }

    public User(String firstName, String lastName, int age, String phoneNumber) {
        if (!phoneNumberPatternChecker(phoneNumber)){
            throw new WrongPhoneNumberException();
        }

        if (ageChecker(age)) {
            throw new WrongAgeException();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
