package com.codegym.casestudy.repository.user_role;

import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.model.user_role.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole,Integer> {
    List<UserRole> findByAccount(Account account);
}
