import java.util.*;
import java.io.*;

public class Passenger {
    private String name;
    private String age;
    private String passportNumber;

    // Constructor
    public Passenger(String age, String name, String passportNumber) {
        this.age = age;
        this.name = name;
        this.passportNumber = passportNumber;
    }

    // Getter for age
    public String getAge() {
        return age;
    }

    // Setter for age
    public void setAge(String age) {
        this.age = age;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for passportNumber
    public String getPassportNumber() {
        return passportNumber;
    }

    // Setter for passportNumber
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}