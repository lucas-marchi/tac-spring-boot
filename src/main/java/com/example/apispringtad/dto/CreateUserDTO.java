package com.example.apispringtad.dto;

import com.example.apispringtad.model.RoleName;

public record CreateUserDTO(String email, String password, RoleName role) {

}
