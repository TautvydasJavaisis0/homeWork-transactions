package com.tietoevry.backend.user.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Setter
@Getter
@Valid
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDto {
    @NotNull(message = "name value can not be empty")
    String username;
    @NotNull(message = "email value can not be empty")
    @Email(message = "value must be email")
    String email;
    @Size(min=6, message = "password must be min 6 max 16")
    @NotNull(message = "password value can not be empty")
    String password;
    @NotNull(message = "cannot be null")
    String firstName;
    @NotNull(message = "cannot be null")
    String lastName;
}
