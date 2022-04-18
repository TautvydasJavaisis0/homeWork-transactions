package com.tietoevry.backend.transaction.model;

import lombok.*;

import javax.validation.Valid;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class TransactionCreationDto {
    String text;
    float amount;
    String senderNumber;
    String receiverNumber;
}
