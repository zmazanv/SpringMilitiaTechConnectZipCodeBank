package bank.connect.tech.controller;

import bank.connect.tech.dto.AccountDTO;
import bank.connect.tech.model.Account;
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


    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched all accounts";
        Iterable<Account> successResponseData = this.accountService.getAllAccounts();
        SuccessResponse<Iterable<Account>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch account as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched account matching the provided account ID: " + accountId;
        Account successResponseData = this.accountService.getAccountById(accountId, exceptionMessage);
        SuccessResponse<Account> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsByCustomerId (@PathVariable Long customerId) {
        String exceptionMessage = "Unable to fetch accounts as no customer was found matching the provided customer ID: " + customerId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched accounts of customer with ID: " + customerId;
        Iterable<Account> successResponseData = this.accountService.getAllAccountsByCustomerId(customerId, exceptionMessage);
        SuccessResponse<Iterable<Account>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/customers/{customerId}/accounts") // TODO: DONE
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @Valid @RequestBody AccountDTO accountDTO) {
        String exceptionMessage = "Unable to create new account as no customer was found matching the provided customer ID: " + customerId;

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new account for customer with ID: " + customerId;
        Account successResponseData = this.accountService.createAccount(customerId, exceptionMessage, accountDTO);
        SuccessResponse<Account> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @Valid @RequestBody Account account) { // this method takes 2 parameters : the account that I want to update and its ID.
        String exceptionMessage = "Unable to update account as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated account matching the provided account ID: " + accountId;
        Account successResponseData = this.accountService.updateAccount(accountId, exceptionMessage, account);
        SuccessResponse<Account> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/accounts/{accountId}") // just deleting - but I should check if it exists kind of like above:
    public ResponseEntity<?> deleteAccount(Long accountId) {
        String exceptionMessage = "Unable to delete account as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.NO_CONTENT.value();
        String successResponseMessage = "Successfully deleted account matching the provided account ID: " + accountId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.accountService.deleteAccount(accountId, exceptionMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT));
    }
}
