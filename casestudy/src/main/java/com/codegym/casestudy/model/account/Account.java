package com.codegym.casestudy.model.account;

import javax.persistence.*;

@Table(name = "accounts")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_email")
    private String email;
    @Column(name = "account_password")
    private String password;
    @Column(name = "is_deleted")
    private boolean isDelete;
    @Column(name = "create_date")
    private String createDate;

    public Account() {
    }

    public Account(int id, String email, String password, boolean isDelete) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isDelete = isDelete;
    }

    public Account(int id, String email, String password, boolean isDelete, String createDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isDelete = isDelete;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        this.isDelete = delete;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
