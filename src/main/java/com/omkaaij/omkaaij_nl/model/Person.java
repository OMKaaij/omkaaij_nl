package com.omkaaij.omkaaij_nl.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Person extends Visitor {

    private String initials;
    private String firstName;
    private String preposition;
    private String surName;
    private String postalCode;
    private String homeNumber;
    private String street;
    private String residence;
    private String email;
    private String phone;



    public Person (long id, String userName, String password, String company, String initials, String firstName, String preposition, String surName, String postalCode,
                   String homeNumber, String street, String residence, String email, String phone) {
        super(id, userName, password, company);
        this.initials = initials;
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
        this.email = email;
        this.phone = phone;
    }


    public Person (String userName, String passWord, String company ,String initials, String firstName, String preposition, String surName,
                   String postalCode, String homeNumber, String street, String residence, String email, String phone) {
        super(ZERO, userName, passWord, company);
        this.initials = initials;
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
        this.email = email;
        this.phone = phone;
    }

    public Person (long id , String initials, String firstName,
                   String preposition, String surName, String postalCode, String homeNumber, String street,
                   String residence, String email, String phone) {
        super (id, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        this.initials = initials;
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
        this.email = email;
        this.phone = phone;
    }

    public Person() {
        super(Visitor.ZERO, Visitor.EMPTY_STRING, Visitor.EMPTY_STRING, Visitor.EMPTY_STRING);
    }

    public Person (String firstName, String preposition, String surName, String email, String phone) {
        this. firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "initials='" + initials + '\'' +
                ", firstName='" + firstName + '\'' +
                ", preposition='" + preposition + '\'' +
                ", surName='" + surName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", street='" + street + '\'' +
                ", residence='" + residence + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
