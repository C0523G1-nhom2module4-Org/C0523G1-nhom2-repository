package com.codegym.casestudy.service.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.repository.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Page<IAccountDto> searchByEmail(Pageable pageable, String keyword) {
        return accountRepository.findAccountByEmailContaining(pageable, "%" + keyword + "%");
    }
}
