package com.codegym.casestudy.repository.contract;

import com.codegym.casestudy.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContractRepository extends JpaRepository<Contract,Integer> {
    @Query(value = "select contracts.*,students.student_name,qualifications.qualification_name " +
            "from contracts join students on contracts.student_id = students.id " +
            "join qualifications on contracts.qualification_id = qualifications.id " +
            "where students.student_name like :search " +
            "or qualifications.qualification_name like :search " +
            "or contracts.contract_date like :search " +
            "order by :sort :condition",nativeQuery = true)
    Page<Contract> findContractBySearch(Pageable pageable,
                                        @Param("search") String search,
                                        @Param("sort") String sort,
                                        @Param("condition") String condition
                                        );

    @Query(value = "select * from contracts where status = 0", nativeQuery = true)
    List<Contract> findAllContract();
}
