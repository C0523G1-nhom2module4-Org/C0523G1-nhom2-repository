package com.codegym.casestudy.service.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {
    Page<IAccountDto> searchByEmail(Pageable pageable, String keyword);
}
