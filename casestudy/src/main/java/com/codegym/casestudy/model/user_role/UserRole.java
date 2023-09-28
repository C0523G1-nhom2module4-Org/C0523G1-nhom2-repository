package com.codegym.casestudy.model.user_role;

import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.model.role.Role;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    public UserRole() {
    }

    public UserRole(int id, Role role, Account account) {
        this.id = id;
        this.role = role;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
