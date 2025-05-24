package vois.securitycoursedemo.security.securedentities;

import org.springframework.security.core.GrantedAuthority;
import vois.securitycoursedemo.entity.Authority;

public record SecurityAuthority(Authority authority) implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authority.getName();
    }
}