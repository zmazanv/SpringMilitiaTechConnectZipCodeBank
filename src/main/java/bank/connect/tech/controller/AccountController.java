package bank.connect.tech.controller;

import bank.connect.tech.dto.AccountDTO;
import bank.connect.tech.model.Account;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.AccountService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/accounts")
    public ResponseEntity<SuccessResponse<Iterable<Account>>> getAllAccounts() {
        int successResponseCode = 200;
        String successResponseMessage = "Success";
        Iterable<Account> successResponseData = this.accountService.getAllAccounts();
        SuccessResponse<Iterable<Account>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("accounts/{accountId}")
    public ResponseEntity<SuccessResponse<Account>> getAccountById(@PathVariable Long accountId) {
        String exceptionMessage = "Error fetching account";

        int successResponseCode = 200;
        String successResponseMessage = "Success";
        Account successResponseData = this.accountService.getAccountById(accountId, exceptionMessage);
        SuccessResponse<Account> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/customer/{customerId}/accounts")
    public ResponseEntity<SuccessResponse<Iterable<Account>>> getAllAccountsByCustomerId (@PathVariable Long customerId) {
        String exceptionMessage = "Error fetching customer's accounts";

        int successResponseCode = 200;
        String successResponseMessage = "Success";
        Iterable<Account> successResponseData = this.accountService.getAllAccountsByCustomerId(customerId, exceptionMessage);
        SuccessResponse<Iterable<Account>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<SuccessResponse<Account>> createAccount(@PathVariable Long customerId, @Valid @RequestBody AccountDTO accountDTO) {
        String exceptionMessage = "Error fetching newly created customer account";

        int successResponseCode = 201;
        String successResponseMessage = "Account created";
        Account successResponseData = this.accountService.createAccount(customerId, exceptionMessage, accountDTO);
        SuccessResponse<Account> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<SuccessResponse<?>> updateAccount(@PathVariable Long accountId, @Valid @RequestBody Account account) { // this method takes 2 parameters : the account that I want to update and its ID.
        String exceptionMessage = "Error";

        int successResponseCode = 200;
        String successResponseMessage = "Customer account updated";
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.accountService.updateAccount(accountId, exceptionMessage, account);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/accounts/{accountId}") // just deleting - but I should check if it exists kind of like above:
    public ResponseEntity<SuccessResponse<?>> deleteAccount(Long accountId) {
        String exceptionMessage = "Account does not exist";

        int successResponseCode = 204;
        String successResponseMessage = "Account successfully deleted";
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.accountService.deleteAccount(accountId, exceptionMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT));
    }
}
