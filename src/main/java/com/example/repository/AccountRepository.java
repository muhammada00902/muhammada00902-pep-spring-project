package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    Account findByUsername(String username);
    
}
