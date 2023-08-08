package com.bancobase.bootcamp.controller;

import com.bancobase.bootcamp.dto.AccountDTO;
import com.bancobase.bootcamp.dto.CustomerInfoDTO;
import com.bancobase.bootcamp.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Account")
@CrossOrigin(origins = {"*"})
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<AccountDTO>> getAccountById(@PathVariable Long customerId){
        List<AccountDTO> account = this.accountService.getAllAccountsByCustomerId(customerId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
