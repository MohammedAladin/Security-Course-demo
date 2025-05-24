package vois.securitycoursedemo.security.usernamepwdauthentication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import vois.securitycoursedemo.repository.CustomerRepository;
import vois.securitycoursedemo.security.userdetailsservice.JpaUserDetailsService;


public class JpaUserDetailsServiceConfigurer extends AbstractHttpConfigurer<JpaUserDetailsServiceConfigurer, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) {
        JpaUserDetailsService userDetailsService = http
                .getSharedObject(ApplicationContext.class)
                .getBean(JpaUserDetailsService.class);

        UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider = new UsernamePwdAuthenticationProvider(userDetailsService);

        http.authenticationProvider(usernamePwdAuthenticationProvider);

    }

    @Bean
    public UserDetailsService userDetailsService(CustomerRepository customerRepository) {
        return new JpaUserDetailsService(customerRepository);
    }


}
