package com.mohanraj.smartcovid19monitring.Bean;

public class AlertBean {
    private String USER_ID,DATE;
    private Double CELSIUS;
    public AlertBean()
    {
    }

    public AlertBean(String USER_ID, String DATE, Double CELSIUS) {
        this.USER_ID = USER_ID;
        this.DATE = DATE;
        this.CELSIUS = CELSIUS;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public Double getCELSIUS() {
        return CELSIUS;
    }

    public void setCELSIUS(Double CELSIUS) {
        this.CELSIUS = CELSIUS;
    }




}
