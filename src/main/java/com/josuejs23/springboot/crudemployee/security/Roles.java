package com.josuejs23.springboot.crudemployee.security;

public enum Roles {
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN"),
    MANAGER("MANAGER");

    private String role;

    public String getRole() {
        return role;
    }

    Roles(String role) {
        this.role = role;
    }
}
