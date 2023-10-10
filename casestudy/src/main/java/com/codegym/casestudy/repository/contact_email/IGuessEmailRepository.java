package com.codegym.casestudy.repository.contact_email;

import com.codegym.casestudy.model.contact_email.GuessEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IGuessEmailRepository extends JpaRepository<GuessEmail, Integer> {

    @Query(value = " update guess_emails set is_deleted = 1 " +
            "where id = :id ", nativeQuery = true)
    void updateGuessEmailById(@Param("id") int id);

    GuessEmail findGuessEmailByEmail(String emailAddress);

    Page<GuessEmail> findGuessEmailBy(Pageable pageable);
}
