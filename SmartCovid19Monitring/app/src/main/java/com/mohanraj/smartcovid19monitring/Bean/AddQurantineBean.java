package com.mohanraj.smartcovid19monitring.Bean;

public class AddQurantineBean  {


    String uid,alerteddate,quarantineddate,qurantinedcenter;
    public AddQurantineBean()
    {}

    public AddQurantineBean(String uid, String alerteddate, String quarantineddate, String qurantinedcenter) {
        this.uid = uid;
        this.alerteddate = alerteddate;
        this.quarantineddate = quarantineddate;
        this.qurantinedcenter = qurantinedcenter;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAlerteddate() {
        return alerteddate;
    }

    public void setAlerteddate(String alerteddate) {
        this.alerteddate = alerteddate;
    }

    public String getQuarantineddate() {
        return quarantineddate;
    }

    public void setQuarantineddate(String quarantineddate) {
        this.quarantineddate = quarantineddate;
    }

    public String getQurantinedcenter() {
        return qurantinedcenter;
    }

    public void setQurantinedcenter(String qurantinedcenter) {
        this.qurantinedcenter = qurantinedcenter;
    }
}
