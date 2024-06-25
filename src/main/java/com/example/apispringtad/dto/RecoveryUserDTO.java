package com.example.apispringtad.dto;

import com.example.apispringtad.model.Role;

import java.util.List;

public record RecoveryUserDTO(Long id, String email, List<Role> roles) {
}
