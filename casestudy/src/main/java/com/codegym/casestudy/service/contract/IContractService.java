package com.codegym.casestudy.service.contract;

import com.codegym.casestudy.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IContractService{
    List<Contract> findAll();
    Contract findById(Integer id);
    void save(Contract contract);
    Page<Contract> findAllBySearch(Pageable pageable, String search, String sort, String condition);
}
