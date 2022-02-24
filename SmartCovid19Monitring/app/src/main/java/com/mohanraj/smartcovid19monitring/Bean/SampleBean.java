package com.mohanraj.smartcovid19monitring.Bean;

public class SampleBean {
    String username,email,password,phoneno,carplatno;

    public  SampleBean()
    {

    }

    public SampleBean(String username, String email, String password, String phoneno, String carplatno) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneno = phoneno;
        this.carplatno = carplatno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getCarplatno() {
        return carplatno;
    }

    public void setCarplatno(String carplatno) {
        this.carplatno = carplatno;
    }
}

