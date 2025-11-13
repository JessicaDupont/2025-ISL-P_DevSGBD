/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * @author jessi
 */
public class Person extends AbstractEntity<Person> {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String mobile;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private Boolean isJuryMember;
    private Boolean isTeacher;

    public Person(String firstName, String lastName, LocalDate dateOfBirth, 
            String email, String mobile, 
            String address, String postalCode, String city, String country, 
            Boolean isJuryMember, Boolean isTeacher) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.isJuryMember = isJuryMember;
        this.isTeacher = isTeacher;
    }

    public Person(Integer id, 
            String firstName, String lastName, LocalDate dateOfBirth, 
            String email, String mobile, 
            String address, String postalCode, String city, String country, 
            Boolean isJuryMember, Boolean isTeacher, 
            LocalDateTime insertedAt, LocalDateTime updatedAt) {
        super(id, insertedAt, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.isJuryMember = isJuryMember;
        this.isTeacher = isTeacher;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsJuryMember() {
        return isJuryMember;
    }

    public void setIsJuryMember(Boolean isJuryMember) {
        this.isJuryMember = isJuryMember;
    }

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    @Override
    public int compareTo(Person o) {
        if (o == null) {
            return 1;
        }
        int idComparison = super.compareTo(o);
        if (idComparison != 0) {
            return idComparison;
        }
        return Comparator
                .comparing(Person::getFirstName, Comparator.nullsLast(String::compareTo))
                .thenComparing(Person::getLastName, Comparator.nullsLast(String::compareTo))
                .thenComparing(Person::getCity, Comparator.nullsLast(String::compareTo))
                .thenComparing(Person::getDateOfBirth, Comparator.nullsLast(LocalDate::compareTo))
                .thenComparing(Person::getEmail, Comparator.nullsLast(String::compareTo))
                .compare(this, o);
    }
}
