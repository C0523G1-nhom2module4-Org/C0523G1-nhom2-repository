package com.codegym.casestudy.service.contract;

import com.codegym.casestudy.model.contract.Contract;
import com.codegym.casestudy.repository.contract.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Page<Contract> findAllBySearch(Pageable pageable, String search) {
        return contractRepository.findContractBySearch(pageable,"%" + search + "%");
    }

    @Override
    public boolean check(Contract contract) {
        List<Contract> list = contractRepository.findAllContract();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == contract.getId()) {
                return true;
            }
        }
        return !LocalDate.parse(contract.getDate()).isBefore(LocalDate.now());
    }

}
