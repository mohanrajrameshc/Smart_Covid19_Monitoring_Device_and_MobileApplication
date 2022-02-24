package com.mohanraj.smartcovid19monitring.Bean;

public class Admin {
    public String NAME;
    public String EMAIL;
    public String PHONE;
    public String DESIGINATION;
    public String PASSWORD;

    public String LOGINTYPE;
    public String DATEOFBIRTH;
    public String GENDER;

    public Admin()
    {

    }

    public Admin(String NAME, String EMAIL, String PHONE, String DESIGINATION, String PASSWORD, String LOGINTYPE, String DATEOFBIRTH, String GENDER) {
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.DESIGINATION = DESIGINATION;
        this.PASSWORD = PASSWORD;
        this.LOGINTYPE = LOGINTYPE;
        this.DATEOFBIRTH = DATEOFBIRTH;
        this.GENDER = GENDER;
    }
}
