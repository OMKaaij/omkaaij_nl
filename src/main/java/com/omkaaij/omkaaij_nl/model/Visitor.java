package com.omkaaij.omkaaij_nl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Visitor {

    public static final String EMPTY_STRING = "";
    public static final long ZERO = 0L;

    private long visitorID;
    private String userName;
    private String passWord;
    private String company;

    public Visitor (String userName, String passWord, String company) {
        this.userName = userName;
        this.passWord = passWord;
        this.company = company;
    }

    public Visitor (long visitorID, String userName, String passWord, String company) {
        this.visitorID = visitorID;
        this.userName = userName;
        this.passWord = passWord;
        this.company = company;
    }

    public Visitor (long id) {
        this.visitorID = id;
    }
}
