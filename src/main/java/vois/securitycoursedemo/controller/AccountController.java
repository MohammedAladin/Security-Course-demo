package vois.securitycoursedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController
{
    @GetMapping("/account")
    public String getAccountDetails() {
        return "Here is the Account Details from the DB";
    }
}
