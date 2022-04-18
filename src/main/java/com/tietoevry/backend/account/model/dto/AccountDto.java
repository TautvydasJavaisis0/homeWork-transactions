package com.tietoevry.backend.account.model.dto;

import lombok.*;

import javax.validation.Valid;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class AccountDto {
    Long id;
    String accountNumber;
    float balance;
}
