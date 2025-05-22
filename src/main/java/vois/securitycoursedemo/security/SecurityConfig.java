package vois.securitycoursedemo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import vois.securitycoursedemo.security.apikey.ApiKeyAuthenticationConfigurer;
import vois.securitycoursedemo.security.userdetailsservice.InMemoryUserDetailsServiceConfig;
import vois.securitycoursedemo.security.userdetailsservice.JdbcUserDetailsServiceConfig;
import vois.securitycoursedemo.security.userdetailsservice.jpa.JpaUserDetailsServiceConfig;
import vois.securitycoursedemo.security.userdetailsservice.jpa.UsernamePwdAuthenticationProvider;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Import(
        {
                JpaUserDetailsServiceConfig.class
                //InMemoryUserDetailsServiceConfig.class,
                //JdbcUserDetailsServiceConfig.class
        }
)
public class SecurityConfig {
    @Autowired
    private UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider;
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http) throws Exception {
        http.authenticationProvider(usernamePwdAuthenticationProvider);
        configureHttpBasic(http);
        configureFormLogin(http);
        configureEndpointsAuthentication(http);

        //configureApiKeyAuthentication(http);
        return http.build();
    }

    private static void configureEndpointsAuthentication(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/account").authenticated()
                        .requestMatchers("/home").permitAll()
                );
    }

    private static void configureFormLogin(HttpSecurity http) throws Exception {
        http.formLogin(withDefaults());
    }

    private static void configureHttpBasic(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
    }
    private static void configureApiKeyAuthentication(HttpSecurity http) throws Exception {
        http.with(new ApiKeyAuthenticationConfigurer(), Customizer.withDefaults());
    }
    @Bean
    PasswordEncoder passwordEncoder() {
//        For learning Spring Security
        return NoOpPasswordEncoder.getInstance();

//        Don't use it directly, give yourself a chance for future upgrade
//        return new BCryptPasswordEncoder();

//        Use this in production
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
