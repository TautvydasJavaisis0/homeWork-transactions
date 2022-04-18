package com.tietoevry.backend.transaction.model;

import com.tietoevry.backend.account.model.dto.AccountDto;
import lombok.*;

import javax.validation.Valid;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class TransactionDto {
    Long id;
    String text;
    float amount;
    Date creationDate;
    AccountDto sender;
    AccountDto receiver;
}
