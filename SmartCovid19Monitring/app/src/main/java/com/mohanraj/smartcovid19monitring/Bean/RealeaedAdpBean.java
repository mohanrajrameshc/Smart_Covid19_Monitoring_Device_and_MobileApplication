package com.mohanraj.smartcovid19monitring.Bean;

public class RealeaedAdpBean {
    String alerted_DATE,center,quarantined_DATE,realese_DATE,userid;
    public RealeaedAdpBean()
    {}

    public RealeaedAdpBean(String alerted_DATE, String center, String quarantined_DATE, String realese_DATE, String userid) {
        this.alerted_DATE = alerted_DATE;
        this.center = center;
        this.quarantined_DATE = quarantined_DATE;
        this.realese_DATE = realese_DATE;
        this.userid = userid;
    }

    public String getAlerted_DATE() {
        return alerted_DATE;
    }

    public void setAlerted_DATE(String alerted_DATE) {
        this.alerted_DATE = alerted_DATE;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getQuarantined_DATE() {
        return quarantined_DATE;
    }

    public void setQuarantined_DATE(String quarantined_DATE) {
        this.quarantined_DATE = quarantined_DATE;
    }

    public String getRealese_DATE() {
        return realese_DATE;
    }

    public void setRealese_DATE(String realese_DATE) {
        this.realese_DATE = realese_DATE;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
