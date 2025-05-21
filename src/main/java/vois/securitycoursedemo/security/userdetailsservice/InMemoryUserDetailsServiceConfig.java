package vois.securitycoursedemo.userdetailsservice;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class InMemoryUserDetailsServiceConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails hamada = User.withUsername("hamada")
                .password("{noop}hamada123") // {noop} is used to indicate that the password is not encoded
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{bcrypt}$2a$12$aoS3KhPIoLfmkROBEOMQ/eaLpnQJDqAYVjPWZ1t0Y3lul2UII9Pb2") // {bcrypt} is used to indicate that the password is encoded with bcrypt
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(hamada, admin);
    }
}
