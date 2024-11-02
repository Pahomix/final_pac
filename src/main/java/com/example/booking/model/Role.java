package com.example.booking.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, ORGANIZER, CLIENT;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
