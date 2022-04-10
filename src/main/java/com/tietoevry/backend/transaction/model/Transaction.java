package com.tietoevry.backend.transaction.model;

import com.tietoevry.backend.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String text;
    protected float amount;
    protected Date creationDate;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    protected Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    protected Account receiver;
}
