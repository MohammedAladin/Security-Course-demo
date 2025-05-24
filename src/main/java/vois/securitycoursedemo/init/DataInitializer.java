package vois.securitycoursedemo.init;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vois.securitycoursedemo.entity.Account;
import vois.securitycoursedemo.entity.Authority;
import vois.securitycoursedemo.entity.Customer;
import vois.securitycoursedemo.repository.AccountRepository;
import vois.securitycoursedemo.repository.AuthorityRepository;
import vois.securitycoursedemo.repository.CustomerRepository;

import java.util.List;
import java.util.Set;
@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializeUsers(AuthorityRepository authorityRepository,
                                             CustomerRepository customerRepository,
                                             AccountRepository accountRepository,
                                             PasswordEncoder passwordEncoder) {

        return args -> {



            customerRepository.deleteAll();
            authorityRepository.deleteAll();
            accountRepository.deleteAll();

            Authority roleAdmin = authorityRepository.save(new Authority("ROLE_ADMIN"));
            Authority roleCustomer = authorityRepository.save(new Authority("ROLE_CUSTOMER"));

            Customer hamada = new Customer(
                    "hamada@example.com",
                    passwordEncoder.encode("password"),
                    Set.of(roleAdmin)
            );
            hamada.setApiKey("ABCDEFGHIJKLMNOPQRSTUVWX-54321"); // Example API Key

            Customer jojo = new Customer(
                    "JohnDoe@example.com",
                    passwordEncoder.encode("password"),
                    Set.of(roleCustomer)
            );
            jojo.setApiKey("ZYXWVUTSRQPONMLKJIHG-12345"); // Example API Key

            Account hamadaAccount = new Account(hamada.getId(), 1000.0);
            Account jojoAccount = new Account(jojo.getId(), 500.0);

            accountRepository.saveAll(List.of(hamadaAccount, jojoAccount));
            customerRepository.saveAll(List.of(hamada, jojo));
        };
    }
}
