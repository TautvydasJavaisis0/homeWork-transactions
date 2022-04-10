package com.tietoevry.backend.account;

import com.tietoevry.backend.account.model.Account;
import com.tietoevry.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query(
        "SELECT DISTINCT i " +
        "FROM Account i " +
        "WHERE i.accountNumber = :accountNumber")
    Account getAccountByNumber(@Param("accountNumber") String accountNumber);

   @Query(
        "SELECT i " +
        "FROM Account i " +
        "WHERE i.user.id = :user")
    List<Account> getAccountsByUser(@Param("user") long user);
}
