package com.codegym.casestudy.repository.qualification;

import com.codegym.casestudy.model.qualification.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IQualificationRepository extends JpaRepository<Qualification, Long> {
    Qualification findQualificationByNameEquals(String qualificationName);

    @Query(value = "select * " +
            " from qualifications " +
            " where (is_deleted = 0) and (qualification_name like :qualificationName) ",
            nativeQuery = true)
    Page<Qualification> findAllQualification(Pageable pageable, @Param("qualificationName") String qualificationName);

    @Transactional
    @Modifying
    @Query(value = "update qualifications " +
            " set is_deleted = 1 " +
            " where id = :qualificationId ",
            nativeQuery = true)
    void deleteById(@Param("qualificationId") Long qualificationId);

    @Query(value = "select * from qualifications " +
            "where is_deleted = 0 ",
            nativeQuery = true)
    List<Qualification> findAll();
}
