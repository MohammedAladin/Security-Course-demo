package vois.securitycoursedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vois.securitycoursedemo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
