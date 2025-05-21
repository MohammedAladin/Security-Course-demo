package vois.securitycoursedemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import vois.securitycoursedemo.userdetailsservice.InMemoryUserDetailsServiceConfig;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Import(
        {
                InMemoryUserDetailsServiceConfig.class
        }
)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/account").authenticated()
                        .requestMatchers("/home").permitAll()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

/*    @Bean
    UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/
}
