package com.tietoevry.backend.user.model;

import lombok.*;

import javax.validation.Valid;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class UserDto {
    Long id;
    String username;
    String email;
    String firstName;
    String lastName;
}