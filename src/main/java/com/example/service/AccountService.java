package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AuthException;
import com.example.exception.DuplicateUserException;
import com.example.exception.InvalidUserException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createNewAccount(Account account) {
        String uname = account.getUsername();
        
        if (accountRepository.existsByUsername(uname)) {
            throw new DuplicateUserException();
        }

        String password = account.getPassword();
        if (!usernameAndPassAreValid(uname, password)) {
            throw new InvalidUserException();
        }

        return accountRepository.save(account);
    }

    public Account loginAccount(Account account) {
        String uname = account.getUsername();
        String password = account.getPassword();
        
        if (accountRepository.existsByUsernameAndPassword(uname, password)) {
            return accountRepository.findByUsername(uname);
        } else {
            throw new AuthException();
        }
    }


    // helpers
    public boolean usernameAndPassAreValid(String uname, String password) {
        
        boolean unameNotBlank = uname != null && uname.length() > 0;
        boolean passwordIsValid = password != null && password.length() > 3;

        return unameNotBlank && passwordIsValid;
    }   
}
