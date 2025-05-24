package vois.securitycoursedemo.dtos;

import vois.securitycoursedemo.entity.Account;

public record AccountResponse(Long id, double balance) {
    public static AccountResponse from(Account account) {
        return new AccountResponse(account.getId(), account.getBalance());
    }
}