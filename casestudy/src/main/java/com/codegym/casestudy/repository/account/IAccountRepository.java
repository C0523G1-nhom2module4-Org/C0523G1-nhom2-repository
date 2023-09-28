package com.codegym.casestudy.repository.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
    Page<IAccountDto> findAccountByEmailContaining(Pageable pageable, String s);
}
