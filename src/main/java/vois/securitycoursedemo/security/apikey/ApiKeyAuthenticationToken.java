package vois.securitycoursedemo.security.apikey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import vois.securitycoursedemo.security.userdetailsservice.jpa.SecurityUser;

import java.util.Collection;

@Getter
public class ApiKeyAuthenticationToken implements Authentication, CredentialsContainer {

    private boolean authenticated;
    private String apiKey;
    private final SecurityUser principal;

    public ApiKeyAuthenticationToken(boolean authenticated, String apiKey, SecurityUser principal) {
        this.authenticated = authenticated;
        this.apiKey = apiKey;
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
    public String getCredentials() {
        return apiKey;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public SecurityUser getPrincipal() {
        return this.principal;
    }

    @Override
    public String getName() {
        return this.principal == null ? null : this.principal.getUsername();
    }

    // Allows ProviderManager to erase the credentials so they don't remain in-memory
    @Override
    public void eraseCredentials() {
        this.apiKey = null;
    }


    public String getApiKey() {
        return apiKey;
    }
}
