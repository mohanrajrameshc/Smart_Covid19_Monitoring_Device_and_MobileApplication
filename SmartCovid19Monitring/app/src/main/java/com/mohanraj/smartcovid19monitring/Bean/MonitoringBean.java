package com.mohanraj.smartcovid19monitring.Bean;

public class MonitoringBean {
    private Float CELSIUS;
    String DATE;
    public MonitoringBean()
    {}

    public MonitoringBean(Float CELSIUS, String DATE) {
        this.CELSIUS = CELSIUS;
        this.DATE = DATE;
    }

    public Float getCELSIUS() {
        return CELSIUS;
    }

    public void setCELSIUS(Float CELSIUS) {
        this.CELSIUS = CELSIUS;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }
}
