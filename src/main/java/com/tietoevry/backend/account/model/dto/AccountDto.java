package com.tietoevry.backend.account.model.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountDto {
    Long id;
    String accountNumber;
    float balance;
}
