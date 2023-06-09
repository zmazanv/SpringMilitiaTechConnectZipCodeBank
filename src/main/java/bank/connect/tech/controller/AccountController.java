package bank.connect.tech.controller;
import bank.connect.tech.domain.Account;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        Iterable<Account> accounts = this.accountService.getAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Optional<Account> account = this.accountService.getAccount(accountId);
        return account.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody Account account) {
        this.accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/accountId")
    public void updateAccount(Account account, Long id) { // this method takes 2 parameters : the account that I want to update and its ID.
        this.accountService.updateAccount(account,id);
    }
    @DeleteMapping("/accountId") // just deleting - but I should check if it exists kind of like above:
    public void deleteAccount(Long accountId) {
        this.accountService.deleteAccount(accountId);
    }
}
