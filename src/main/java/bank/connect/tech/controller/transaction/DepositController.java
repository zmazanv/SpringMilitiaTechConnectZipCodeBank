package bank.connect.tech.controller.transaction;

import bank.connect.tech.TechConnectZipCodeBankApplication;
import bank.connect.tech.dto.create.TransactionCreateDTO;
import bank.connect.tech.dto.update.TransactionUpdateDTO;
import bank.connect.tech.model.Transaction;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.transaction.DepositService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;


    @GetMapping("/deposits") //
    public ResponseEntity<?> getAllDeposits() {
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched all deposits";
        Iterable<Transaction> successResponseData = this.depositService.getAllDeposits();
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDepositsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch deposits as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched deposits matching the provided account ID: " + accountId;
        Iterable<Transaction> successResponseData = this.depositService.getAllDepositsByAccountId(accountId, exceptionMessage);
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/deposits/{transactionId}")
    public ResponseEntity<?> getDepositById(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to fetch deposit as no deposit was found matching the provided deposit ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched deposit matching the provided deposit ID: " + transactionId;
        Transaction successResponseData = this.depositService.getDepositById(transactionId, exceptionMessage);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?>createDeposit(@PathVariable Long accountId, @Valid @RequestBody TransactionCreateDTO transactionCreateDTO) {
        String exceptionMessage = "Unable to create new deposit as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new deposit for account with ID: " + accountId;
        Transaction successResponseData = this.depositService.createDeposit(accountId, exceptionMessage, transactionCreateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/deposits/{transactionId}")
    public ResponseEntity<?> updateDeposit(@PathVariable Long transactionId, @Valid @RequestBody TransactionUpdateDTO transactionUpdateDTO) {
        String exceptionMessage = "Unable to update deposit as no deposit was found matching the provided deposit ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated deposit matching the provided deposit ID: " + transactionId;
        Transaction successResponseData = this.depositService.updateDeposit(transactionId, exceptionMessage, transactionUpdateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/deposits/{transactionId}")
    public ResponseEntity<?> cancelDeposit(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to delete deposit as no deposit was found matching the provided deposit ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully deleted deposit matching the provided deposit ID: " + transactionId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.depositService.cancelDeposit(transactionId, exceptionMessage);
        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
