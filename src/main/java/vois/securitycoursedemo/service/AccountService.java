package vois.securitycoursedemo.service;

import vois.securitycoursedemo.dtos.CreateAccountRequest;
import org.springframework.stereotype.Service;
import vois.securitycoursedemo.entity.Account;
import vois.securitycoursedemo.repository.AccountRepository;
import vois.securitycoursedemo.security.SecuritySupport;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account>  getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(CreateAccountRequest accountRequest){
        Account createdAccount = new Account(SecuritySupport.getCurrentUserId(), accountRequest.balance());
        return accountRepository.save(createdAccount);
    }

}
