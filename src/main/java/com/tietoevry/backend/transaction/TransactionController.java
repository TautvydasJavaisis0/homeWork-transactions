package com.tietoevry.backend.transaction;

import com.tietoevry.backend.transaction.model.TransactionCreationDto;
import com.tietoevry.backend.transaction.model.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping(path = "/sender/{accountId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<TransactionDto> getAllSendTransactions(@PathVariable Long accountId) {
        return transactionService.getAllSendTransactionByAccount(accountId);
    }

    @GetMapping(path = "/receiver/{accountId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<TransactionDto> getAllReceivedTransactions(@PathVariable Long accountId) {
        return transactionService.getAllReceivedTransactionByAccount(accountId);
    }

    @GetMapping(path = "/{accountId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<TransactionDto> getAllTransactions(@PathVariable Long accountId) {
        return transactionService.getAllTransactionByAccount(accountId);
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public TransactionDto sendTransaction(@RequestBody TransactionCreationDto transaction) {
        return transactionService.createTransaction(transaction);
    }
}
