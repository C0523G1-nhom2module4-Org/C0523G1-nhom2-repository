package com.codegym.casestudy.dto.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  account_email;
    private String  account_password;
    private boolean status;

    public Account() {
    }

    public Account(int id, String account_email, String account_password) {
        this.id = id;
        this.account_email = account_email;
        this.account_password = account_password;
    }

    public Account(int id, String account_email, String account_password, boolean status) {
        this.id = id;
        this.account_email = account_email;
        this.account_password = account_password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
