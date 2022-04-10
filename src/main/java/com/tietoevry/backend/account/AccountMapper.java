package com.tietoevry.backend.account;

import com.tietoevry.backend.account.model.Account;
import com.tietoevry.backend.account.model.dto.AccountDto;

public class AccountMapper {

    private AccountMapper() {
    }

    public static AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
            .id(account.getId())
            .accountNumber(account.getAccountNumber())
            .balance(account.getBalance())
            .build();
    }

    public static Account toAccount(AccountDto accountDto){
        return  Account.builder()
            .id(accountDto.getId())
            .accountNumber(accountDto.getAccountNumber())
            .balance(accountDto.getBalance())
            .build();
    }
}
