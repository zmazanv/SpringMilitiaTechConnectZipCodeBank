package bank.connect.tech.controller;

import bank.connect.tech.dto.errors.ApiResponse;
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
    public ResponseEntity<ApiResponse<Iterable<Account>>> getAllAccounts() {
        Iterable<Account> accounts = accountService.getAllAccounts();
        ApiResponse<Iterable<Account>> response = new ApiResponse<Iterable<Account>>();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(accounts);

        return ResponseEntity.ok(response);
    }

    @GetMapping("accounts/{accountId}")
    public ApiResponse<Account> getAccountById(@PathVariable Long accountId, @PathVariable int code) {
        Account account = accountService.getAccountById(accountId);
        ApiResponse<Account> response = new ApiResponse<>(code, "Success", account);

        return response;
    }

    @GetMapping("/customer/{customerId}/accounts")
    public ResponseEntity<ApiResponse<Iterable<Account>>> getAllAccountsByCustomerId(@PathVariable Long customerId, @PathVariable int code) {
        Iterable<Account> accounts = accountService.getAllAccountsByCustomerId(customerId);
        ApiResponse<Iterable<Account>> response = new ApiResponse<>(code, "Success", accounts);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<ApiResponse<Void>> createAccount(@PathVariable Long customerId, @Valid @RequestBody Account account) {
        accountService.createAccount(customerId, account);
        ApiResponse<Void> response = new ApiResponse<>(201, "Account created successfully", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<ApiResponse<Void>> updateAccount(@PathVariable Long accountId, @Valid @RequestBody Account account) {
        accountService.updateAccount(accountId, account);
        ApiResponse<Void> response = new ApiResponse<>(200, "Account updated successfully", null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        ApiResponse<Void> response = new ApiResponse<>(204, "Account deleted successfully", null);
        return ResponseEntity.ok(response);
    }

}
