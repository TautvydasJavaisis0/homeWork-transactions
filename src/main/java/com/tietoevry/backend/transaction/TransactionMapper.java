package com.tietoevry.backend.transaction;

import com.tietoevry.backend.transaction.model.Transaction;
import com.tietoevry.backend.transaction.model.TransactionDto;

import static com.tietoevry.backend.account.AccountMapper.toAccount;
import static com.tietoevry.backend.account.AccountMapper.toAccountDto;

public class TransactionMapper {
    public static TransactionDto toTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
            .id(transaction.getId())
            .text(transaction.getText())
            .amount(transaction.getAmount())
            .creationDate(transaction.getCreationDate())
            .receiver(transaction.getReceiver() == null ? null : toAccountDto(transaction.getReceiver()))
            .sender(transaction.getSender() == null ? null : toAccountDto(transaction.getSender()))
            .build();
    }

    public static Transaction toTransaction(TransactionDto transactionDto) {
        return Transaction.builder()
            .id(transactionDto.getId())
            .text(transactionDto.getText())
            .amount(transactionDto.getAmount())
            .creationDate(transactionDto.getCreationDate())
            .receiver(transactionDto.getReceiver() == null ? null : toAccount(transactionDto.getReceiver()))
            .sender(transactionDto.getSender() == null ? null : toAccount(transactionDto.getSender()))
            .build();
    }
}
