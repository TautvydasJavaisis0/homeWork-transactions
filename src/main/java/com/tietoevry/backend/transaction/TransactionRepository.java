package com.tietoevry.backend.transaction;

import com.tietoevry.backend.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(
        "SELECT t " +
        "FROM Transaction t " +
        "WHERE t.receiver.id = :account_id OR " +
        "t.sender.id = :account_id " +
        "ORDER BY t.creationDate DESC")
    List<Transaction> getAllAccountTransaction(@Param("account_id") long accountID);

    @Query(
        "SELECT t " +
        "FROM Transaction t " +
        "WHERE t.sender.id = :account_id " +
        "ORDER BY t.creationDate DESC")
    List<Transaction> getAllSendTransaction(@Param("account_id") long accountID);

    @Query(
        "SELECT t " +
        "FROM Transaction t " +
        "WHERE t.receiver.id = :account_id " +
        "ORDER BY t.creationDate DESC")
    List<Transaction> getAllReceivedTransaction(@Param("account_id") long accountID);

}
