package com.mohanraj.smartcovid19monitring.Bean;

import com.mohanraj.smartcovid19monitring.Admin.AddReleasedList;

public class AddRealeasedBean {
    String USERID,ALERTED_DATE,QUARANTINED_DATE,REALESE_DATE,CENTER;
    public AddRealeasedBean()
    {}

    public AddRealeasedBean(String USERID, String ALERTED_DATE, String QUARANTINED_DATE, String REALESE_DATE, String CENTER) {
        this.USERID = USERID;
        this.ALERTED_DATE = ALERTED_DATE;
        this.QUARANTINED_DATE = QUARANTINED_DATE;
        this.REALESE_DATE = REALESE_DATE;
        this.CENTER = CENTER;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getALERTED_DATE() {
        return ALERTED_DATE;
    }

    public void setALERTED_DATE(String ALERTED_DATE) {
        this.ALERTED_DATE = ALERTED_DATE;
    }

    public String getQUARANTINED_DATE() {
        return QUARANTINED_DATE;
    }

    public void setQUARANTINED_DATE(String QUARANTINED_DATE) {
        this.QUARANTINED_DATE = QUARANTINED_DATE;
    }

    public String getREALESE_DATE() {
        return REALESE_DATE;
    }

    public void setREALESE_DATE(String REALESE_DATE) {
        this.REALESE_DATE = REALESE_DATE;
    }

    public String getCENTER() {
        return CENTER;
    }

    public void setCENTER(String CENTER) {
        this.CENTER = CENTER;
    }
}
