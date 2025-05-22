package vois.securitycoursedemo.security.userdetailsservice.jpa;

import org.springframework.security.core.GrantedAuthority;
import vois.securitycoursedemo.entity.Authority;

public record SecurityAuthority(Authority authority) implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authority.getName();
    }
}