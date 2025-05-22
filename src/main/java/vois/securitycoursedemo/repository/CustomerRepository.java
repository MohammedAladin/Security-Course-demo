package vois.securitycoursedemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vois.securitycoursedemo.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    // findby -> it is a convention in Spring Data JPA to find the data, then check the column name in the DB which is 'Email' in this case

    Optional<Customer> findByApiKey(String apiKey);
}
