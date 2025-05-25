package vois.securitycoursedemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vois.securitycoursedemo.security.apikey.ApiKeyAuthenticationConfigurer;
import vois.securitycoursedemo.security.jwt.JwtAuthenticationConfigurer;
import vois.securitycoursedemo.security.usernamepwdauthentication.JpaUserDetailsServiceConfigurer;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Import(
        {
                //InMemoryUserDetailsServiceConfig.class,
                //JdbcUserDetailsServiceConfig.class
        }
)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        configureHttpBasic(http);
        configureJpaUserDetailsWithUserNameAndPwdProvider(http);
        //configureFormLogin(http);
        configureEndpointsAuthentication(http);
        configureApiKeyAuthentication(http);
        configureJwtProvider(http);
        return http.build();
    }

    private static void configureEndpointsAuthentication(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/sec/*").authenticated()
                        .requestMatchers("/home", "/auth/*").permitAll()
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
    private static void configureJpaUserDetailsWithUserNameAndPwdProvider(HttpSecurity http) throws Exception {
        http.with(new JpaUserDetailsServiceConfigurer(), Customizer.withDefaults());
    }
    private static void configureJwtProvider(HttpSecurity http) throws Exception {
        http.with(new JwtAuthenticationConfigurer(), Customizer.withDefaults());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
//        For learning Spring Security
        return NoOpPasswordEncoder.getInstance(); // deprecated, but works for learning purposes

//        Don't use it directly, give yourself a chance for future upgrade
//        return new BCryptPasswordEncoder();

//        Use this in production
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
