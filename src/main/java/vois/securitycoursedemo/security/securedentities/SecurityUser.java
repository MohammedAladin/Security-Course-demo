package vois.securitycoursedemo.security.securedentities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vois.securitycoursedemo.entity.Customer;

import java.util.Collection;

public record SecurityUser(Customer customer) implements UserDetails {

    public Long getId() {
        return this.customer().getId();
    }

    @Override
    public String getUsername() {
        return this.customer.getEmail();
    }

    @Override
    public String getPassword() {
        return this.customer.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.getAuthorities()
                .stream()
                .map(SecurityAuthority::new)
                .toList();
    }
}
