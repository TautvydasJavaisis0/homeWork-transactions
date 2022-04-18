package com.tietoevry.backend.session.model;

import lombok.Data;

@Data
public class LoginData {
    private String email;
    private String password;
}
