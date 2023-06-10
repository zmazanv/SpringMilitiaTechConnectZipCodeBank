package bank.connect.tech.controller;

import bank.connect.tech.model.Account;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.service.AccountService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.logging.Logger;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    private static final Logger logger = Logger.getGlobal();

    @GetMapping("/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        logger.info("Attempting to get account");
        return (new ResponseEntity<>(this.accountService.getAllAccounts(), HttpStatus.OK));
    }

    @GetMapping("accounts/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        return (new ResponseEntity<>(this.accountService.getAccountById(accountId), HttpStatus.OK));
    }

    @GetMapping("/customer/{customerId}/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccountsByCustomerId (@PathVariable Long customerId) {
        return (new ResponseEntity<>(this.accountService.getAllAccountsByCustomerId(customerId),HttpStatus.OK));
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable Long customerId, @Valid @RequestBody Account account) {
        this.accountService.createAccount(customerId, account);
        return (new ResponseEntity<>(HttpStatus.CREATED));
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable Long accountId, @Valid Account account) { // this method takes 2 parameters : the account that I want to update and its ID.
        this.accountService.updateAccount(accountId, account);
        return (new ResponseEntity<>(HttpStatus.OK));
    }

    @DeleteMapping("/accounts/{accountId}") // just deleting - but I should check if it exists kind of like above:
    public ResponseEntity<Void> deleteAccount(Long accountId) {
        this.accountService.deleteAccount(accountId);
        return (new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
