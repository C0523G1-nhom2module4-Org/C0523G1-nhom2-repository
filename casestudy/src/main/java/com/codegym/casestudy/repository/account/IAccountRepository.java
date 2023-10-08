package com.codegym.casestudy.repository.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
    @Query(value = "select account_id as id, user_name as email,create_date as date, role_name as roleName " +
            "from accounts " +
            "join user_roles on accounts.id = user_roles.account_id " +
            "join roles on user_roles.role_id = roles.id " +
            "where accounts.is_deleted = false " +
            "and user_name like :keyword", nativeQuery = true)
//    @Query(value = " select  * from accounts where account_email like :keyword and is_delete = 0 ",
//    nativeQuery = true)
    Page<IAccountDto> findAccountByEmailContaining(Pageable pageable, @Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(value = "update accounts set accounts.is_deleted = true where accounts.id = :id",nativeQuery = true)
    void deleteAccount(@Param("id") Account account);

//    @Query(value = "select account_email from account where account_email = :email",nativeQuery = true)
    Account findAccountByEmail( String email);


    @Modifying
    @Transactional
//    @Query(value = "insert into account (account_email," +
//            "account_password," +
//            "account_status)values(:email,:pass,true)",nativeQuery = true)
    @Query(value = "call add_account(:p_email, :p_password,:p_create_date)", nativeQuery = true)
//    @Query(value = "insert into accounts (account_email,account_password,create_date,) values (:email,:pass,:date)",nativeQuery = true)
    void addAccount(@Param("p_email") String email, @Param("p_password") String newPass, @Param("p_create_date") String createDate);
}
