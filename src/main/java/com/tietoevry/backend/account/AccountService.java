package com.tietoevry.backend.account;

import com.tietoevry.backend.account.model.Account;
import com.tietoevry.backend.account.model.dto.AccountDto;
import com.tietoevry.backend.transaction.TransactionMapper;
import com.tietoevry.backend.transaction.model.Transaction;
import com.tietoevry.backend.transaction.model.TransactionDto;
import com.tietoevry.backend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountService {

   private final AccountRepository accountRepository;

    public List<AccountDto> getAccountsDto(long id) {
        List<Account> accounts = accountRepository.getAccountsByUser(id);

        List<AccountDto> accountDtoList = new ArrayList<>();

        for(Account account: accounts){
            accountDtoList.add(AccountMapper.toAccountDto(account));
        }

        return accountDtoList;
    }

    public void createFirstAccountForUser(User user) {
        Account newAccount = Account.builder()
                .accountNumber(getNewAccountNumber())
                .balance(10)
                .user(user)
                .build();
        accountRepository.save(newAccount);
    }

    public AccountDto createAccountForUser(User user) {
        Account newAccount = Account.builder()
                .accountNumber(getNewAccountNumber())
                .balance(0)
                .user(user)
                .build();
        newAccount = accountRepository.save(newAccount);
        return AccountMapper.toAccountDto(newAccount);
    }

    public void deleteAccount(long user) {
        accountRepository.deleteById(user);
    }

    public String getNewAccountNumber() {
        StringBuilder number = new StringBuilder("LT");

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            number.append(rand.nextInt(10));
        }

        return number.toString();
    }
}
