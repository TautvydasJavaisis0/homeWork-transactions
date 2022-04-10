package com.tietoevry.backend.transaction.model;

import com.tietoevry.backend.account.model.dto.AccountDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Date;

@Builder
@Setter
@Getter
@Valid
public class TransactionDto {
    Long id;
    String text;
    float amount;
    Date creationDate;
    AccountDto sender;
    AccountDto receiver;
}
