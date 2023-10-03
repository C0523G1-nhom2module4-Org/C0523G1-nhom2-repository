package com.codegym.casestudy.repository.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
    @Query(value = "select account_id as id, account_email as email, role_name as roleName " +
            "from account " +
            "join user_role on account.id = user_role.account_id " +
            "join role on user_role.role_id = role.id " +
            "where account.account_status = true " +
            "and account_email like :keyword", nativeQuery = true)
    Page<IAccountDto> findAccountByEmailContaining(Pageable pageable, @Param("keyword") String keyword);

}
