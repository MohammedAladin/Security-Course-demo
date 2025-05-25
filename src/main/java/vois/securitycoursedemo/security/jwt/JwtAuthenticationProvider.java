package vois.securitycoursedemo.security.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import vois.securitycoursedemo.security.securedentities.SecurityUser;
import vois.securitycoursedemo.security.userdetailsservice.JpaUserDetailsService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JpaUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationProvider(JpaUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String username = jwtUtils.getUsernameFromToken(jwtAuthenticationToken.getJwtToken());

        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(username);
        if (securityUser == null) {
            throw new BadCredentialsException("Invalid API key");
        }

        if (!jwtUtils.isValidToken(jwtAuthenticationToken.getJwtToken())) {
            throw new BadCredentialsException("Invalid Token");
        }

        return new JwtAuthenticationToken(true, null, securityUser);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));

    }
}
