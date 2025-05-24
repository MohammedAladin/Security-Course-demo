package vois.securitycoursedemo.security.usernamepwdauthentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vois.securitycoursedemo.security.userdetailsservice.JpaUserDetailsService;

@Component
public class UsernamePwdAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final JpaUserDetailsService userDetailsService;

    public UsernamePwdAuthenticationProvider(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String presentedPassword = authentication.getCredentials().toString();
        if (!userDetails.getPassword().equals(presentedPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("User not found with username: " + username);
        }
        return userDetails;
    }
}
