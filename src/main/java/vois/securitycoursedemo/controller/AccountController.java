package vois.securitycoursedemo.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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



    public record AccountResponse(Long id, double balance) {
        public static AccountResponse from(Account account) {
            return new AccountResponse(account.getId(), account.getBalance());
        }
    }
    public record CreateAccountRequest(double balance) {
    }
}
