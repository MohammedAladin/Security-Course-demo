package vois.securitycoursedemo.security.apikey;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import vois.securitycoursedemo.security.userdetailsservice.JpaUserDetailsService;
import vois.securitycoursedemo.security.securedentities.SecurityUser;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    private final JpaUserDetailsService userDetailsService;

    public ApiKeyAuthenticationProvider(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthenticationToken apiKeyAuth = (ApiKeyAuthenticationToken) authentication;
        SecurityUser securityUser = (SecurityUser) userDetailsService.loadByApiKey(apiKeyAuth.getApiKey());
        if (securityUser == null) {
            throw new BadCredentialsException("Invalid API key");
        }

        return new ApiKeyAuthenticationToken(true, null, securityUser);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ApiKeyAuthenticationToken.class.isAssignableFrom(authentication));

    }
}
