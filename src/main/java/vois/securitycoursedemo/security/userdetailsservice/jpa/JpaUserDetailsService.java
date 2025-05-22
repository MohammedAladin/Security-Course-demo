package vois.securitycoursedemo.security.userdetailsservice.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vois.securitycoursedemo.entity.Customer;
import vois.securitycoursedemo.repository.CustomerRepository;

import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    public JpaUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public UserDetails loadByApiKey(String apiKey) {
        return customerRepository.findByApiKey(apiKey)
                .map(SecurityUser::new)
                .orElseThrow(() -> new BadCredentialsException("API Key not found " + apiKey));
    }
}
