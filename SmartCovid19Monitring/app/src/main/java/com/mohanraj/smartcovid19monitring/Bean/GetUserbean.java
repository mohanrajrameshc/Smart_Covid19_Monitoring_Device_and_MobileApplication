package com.mohanraj.smartcovid19monitring.Bean;

public class GetUserbean {
    String NAME,IP_ADDRESS,ADDRESS,EMAIL,PHONE,DESIGINATION,GENDER,DATEOFBIRTH;
    public GetUserbean()
    {}

    public GetUserbean(String NAME, String IP_ADDRESS, String ADDRESS, String EMAIL, String PHONE, String DESIGINATION, String GENDER, String DATEOFBIRTH) {
        this.NAME = NAME;
        this.IP_ADDRESS = IP_ADDRESS;
        this.ADDRESS = ADDRESS;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.DESIGINATION = DESIGINATION;
        this.GENDER = GENDER;
        this.DATEOFBIRTH = DATEOFBIRTH;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getDESIGINATION() {
        return DESIGINATION;
    }

    public void setDESIGINATION(String DESIGINATION) {
        this.DESIGINATION = DESIGINATION;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getDATEOFBIRTH() {
        return DATEOFBIRTH;
    }

    public void setDATEOFBIRTH(String DATEOFBIRTH) {
        this.DATEOFBIRTH = DATEOFBIRTH;
    }
}
