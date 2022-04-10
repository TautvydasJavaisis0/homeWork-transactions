package com.tietoevry.backend.account.model;

import com.tietoevry.backend.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private float balance;
    @ManyToOne
    private User user;
}
