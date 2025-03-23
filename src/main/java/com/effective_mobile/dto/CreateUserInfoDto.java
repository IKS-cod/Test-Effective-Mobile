package com.effective_mobile.dto;

import com.effective_mobile.enums.Role;

public class CreateUserInfoDto {
    private String email;
    private String password;
    private Role role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}