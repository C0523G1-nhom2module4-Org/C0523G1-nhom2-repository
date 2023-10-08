package com.codegym.casestudy.service.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {
    Page<IAccountDto> searchByEmail(Pageable pageable, String keyword);


    Account findById(int deleteId);

    void deleteAccount(Account account);

    Account findByEmail(String email);

    void addAccount(Account account);

    boolean testPass(String email, String currentPassword);

    void changePass(String email, String newPassword);

    void sendEmail(String to, String subject, String body);

    String sendEmailAndReturnCode(String email);

    void forgotPassword(Account account);

    void createAccount(Account newAccount, int role);
}
