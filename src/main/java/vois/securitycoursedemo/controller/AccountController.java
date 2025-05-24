package vois.securitycoursedemo.controller;

import vois.securitycoursedemo.dtos.AccountResponse;
import vois.securitycoursedemo.dtos.CreateAccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vois.securitycoursedemo.entity.Account;

import vois.securitycoursedemo.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/sec")
public class AccountController
{

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Account Service!");
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> getAccounts() {
        List<AccountResponse> response =  accountService.getAllAccounts()
                .stream()
                .map(AccountResponse::from)
                .toList();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest accountRequest) {
        Account createdAccount = accountService.createAccount(accountRequest);
        return ResponseEntity.ok(AccountResponse.from(createdAccount));
    }



}
