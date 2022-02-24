package com.mohanraj.smartcovid19monitring.Bean;

public class User {
    public String NAME;
    public String EMAIL;
    public String PHONE;
    public String ADDRESS;
    public String PASSWORD;
    public String LOGINTYPE;
    public String DATEOFBIRTH;
    public String GENDER;

    public User()
    {}

    public User(String NAME, String EMAIL, String PHONE, String ADDRESS, String PASSWORD, String LOGINTYPE, String DATEOFBIRTH, String GENDER) {
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
        this.PASSWORD = PASSWORD;
        this.LOGINTYPE = LOGINTYPE;
        this.DATEOFBIRTH = DATEOFBIRTH;
        this.GENDER = GENDER;
    }
}
