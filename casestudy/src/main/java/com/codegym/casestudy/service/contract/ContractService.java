package com.codegym.casestudy.service.contract;

import com.codegym.casestudy.model.contract.Contract;
import com.codegym.casestudy.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService implements IContractService{
    @Autowired
    private IContractRepository contractRepository;
    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findById(Integer id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public void save(Contract contract) {
        contractRepository.save(contract);
    }


    @Override
    public Page<Contract> findAllBySearch(Pageable pageable, String search, String sort, String condition) {
        return contractRepository.findContractBySearch(pageable,"%" + search + "%",sort,condition);
    }
}
