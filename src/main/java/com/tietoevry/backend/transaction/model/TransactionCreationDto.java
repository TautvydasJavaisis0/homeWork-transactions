package com.tietoevry.backend.transaction.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Builder
@Setter
@Getter
@Valid
public class TransactionCreationDto {
    String text;
    float amount;
    String senderNumber;
    String receiverNumber;
}
