package ua.metlife.claims.simplecs.entity.crl;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, MASTER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

