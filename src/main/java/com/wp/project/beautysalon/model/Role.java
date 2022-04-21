package com.wp.project.beautysalon.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_EMPLOYEE,
    ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name(); // ne znam kakvo name e
    }
}
