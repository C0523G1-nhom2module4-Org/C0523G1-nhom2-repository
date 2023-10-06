package com.codegym.casestudy.service.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.repository.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private IAccountRepository accountRepository;


    @Override
    public Page<IAccountDto> searchByEmail(Pageable pageable, String keyword) {
        return accountRepository.findAccountByEmailContaining(pageable, "%" + keyword + "%");
    }

    @Override
    public Account findById(int deleteId) {
        return accountRepository.findById(deleteId).orElse(null);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.deleteAccount(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public void addAccount(Account account) {
        String newPass = passwordEncoder.encode(account.getPassword());
        accountRepository.addAccount(account.getEmail(), newPass, account.isStatus());
    }

    @Override
    public boolean testPass(String email, String currentPassword) {
        Account account = accountRepository.findAccountByEmail(email);
        System.out.println(email);
        String oldPassEncoder = account.getPassword();
        boolean isMatch = passwordEncoder.matches(currentPassword, oldPassEncoder);
        System.out.println(isMatch);
        return isMatch;
    }

    @Override
    public void changePass(String email, String newPassword) {
        Account account = accountRepository.findAccountByEmail(email);
        System.out.println(passwordEncoder.encode(newPassword));
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }


}
