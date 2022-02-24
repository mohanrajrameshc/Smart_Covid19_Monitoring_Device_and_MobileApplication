package com.mohanraj.smartcovid19monitring.Bean;

import com.mohanraj.smartcovid19monitring.RecyclerView.UserHistoryAdapter;

public class UserHistoryAdapterBean {
    Double CELSIUS;
    String DATE,TIME;

    public UserHistoryAdapterBean()
    {}

    public UserHistoryAdapterBean(Double CELSIUS, String DATE, String TIME) {
        this.CELSIUS = CELSIUS;
        this.DATE = DATE;
        this.TIME = TIME;
    }

    public Double getCELSIUS() {
        return CELSIUS;
    }

    public void setCELSIUS(Double CELSIUS) {
        this.CELSIUS = CELSIUS;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
