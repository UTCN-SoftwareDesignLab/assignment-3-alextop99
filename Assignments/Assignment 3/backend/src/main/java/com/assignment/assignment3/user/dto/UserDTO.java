package com.assignment.assignment3.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String username;
    private String email;
    private String password;
    private String role;
}
