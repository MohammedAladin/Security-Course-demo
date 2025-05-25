package vois.securitycoursedemo.security.jwt;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import vois.securitycoursedemo.security.securedentities.SecurityUser;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken implements Authentication, CredentialsContainer {

    private boolean authenticated;
    private String jwtToken;
    private final SecurityUser principal;

    public JwtAuthenticationToken(boolean authenticated, String apiKey, SecurityUser principal) {
        this.authenticated = authenticated;
        this.jwtToken = apiKey;
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.principal.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public String getName() {
        return this.principal == null ? null : this.principal.getUsername();
    }

    // Allows ProviderManager to erase the credentials so they don't remain in-memory
    @Override
    public void eraseCredentials() {
        this.jwtToken = null;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public SecurityUser getPrincipal() {
        return principal;
    }
}
