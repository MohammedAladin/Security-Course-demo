package vois.securitycoursedemo.security.jwt;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import vois.securitycoursedemo.security.apikey.ApiKeyAuthenticationFilter;
import vois.securitycoursedemo.security.userdetailsservice.JpaUserDetailsService;

public class JwtAuthenticationConfigurer extends AbstractHttpConfigurer<JwtAuthenticationConfigurer, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) {

        JpaUserDetailsService userDetailsService = http
                .getSharedObject(ApplicationContext.class)
                .getBean(JpaUserDetailsService.class);

        JwtUtils jwtUtils = http
                .getSharedObject(ApplicationContext.class)
                .getBean(JwtUtils.class);

        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(userDetailsService, jwtUtils);

        http.authenticationProvider(jwtAuthenticationProvider);
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        JwtAuthenticationFilter apiKeyAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager);
        http.addFilterBefore(apiKeyAuthenticationFilter, BasicAuthenticationFilter.class);
    }
}
