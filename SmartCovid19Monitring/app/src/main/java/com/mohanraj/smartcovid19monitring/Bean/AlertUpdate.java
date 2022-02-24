package com.mohanraj.smartcovid19monitring.Bean;

public class AlertUpdate {
    String STATUS,DATE,USER_ID;
    Double CELSIUS;
    public AlertUpdate()

    {}

    public AlertUpdate(String STATUS, String DATE, String USER_ID, Double CELSIUS) {
        this.STATUS = STATUS;
        this.DATE = DATE;
        this.USER_ID = USER_ID;
        this.CELSIUS = CELSIUS;
    }
}
