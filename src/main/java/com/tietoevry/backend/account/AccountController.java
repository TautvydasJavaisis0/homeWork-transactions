package com.tietoevry.backend.account;

import com.tietoevry.backend.account.model.dto.AccountDto;
import com.tietoevry.backend.user.UserRepository;
import com.tietoevry.backend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final UserRepository userRepository;

    @GetMapping(path = "/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<AccountDto> getAccount(@PathVariable Long userId) {
        return accountService.getAccountsDto(userId);
    }

    @PostMapping(path = "/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public AccountDto createAccount(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId));
        return accountService.createAccountForUser(user);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping(path = "/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
