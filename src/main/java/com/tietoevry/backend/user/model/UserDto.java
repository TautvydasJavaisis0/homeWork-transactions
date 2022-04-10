package com.tietoevry.backend.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Builder
@Setter
@Getter
@Valid
public class UserDto {
    Long id;
    String username;
    String email;
    String firstName;
    String lastName;
}