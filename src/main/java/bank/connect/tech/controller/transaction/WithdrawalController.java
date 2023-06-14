package bank.connect.tech.controller.transaction;

import bank.connect.tech.TechConnectZipCodeBankApplication;
import bank.connect.tech.dto.create.TransactionCreateDTO;
import bank.connect.tech.dto.update.TransactionUpdateDTO;
import bank.connect.tech.model.Transaction;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.transaction.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;


    @GetMapping("/withdrawals") //
    public ResponseEntity<?> getAllWithdrawals() {
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched all withdrawals";
        Iterable<Transaction> successResponseData = this.withdrawalService.getAllWithdrawals();
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch withdrawals as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched withdrawals matching the provided account ID: " + accountId;
        Iterable<Transaction> successResponseData = this.withdrawalService.getAllWithdrawalsByAccountId(accountId, exceptionMessage);
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/withdrawals/{transactionId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to fetch withdrawal as no withdrawal was found matching the provided withdrawal ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched withdrawal matching the provided withdrawal ID: " + transactionId;
        Transaction successResponseData = this.withdrawalService.getWithdrawalById(transactionId, exceptionMessage);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?>createWithdrawal(@PathVariable Long accountId, @Valid @RequestBody TransactionCreateDTO transactionCreateDTO) {
        String exceptionMessage = "Unable to create new withdrawal as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new withdrawal for account with ID: " + accountId;
        Transaction successResponseData = this.withdrawalService.createWithdrawal(accountId, exceptionMessage, transactionCreateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/withdrawals/{transactionId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long transactionId, @Valid @RequestBody TransactionUpdateDTO transactionUpdateDTO) {
        String exceptionMessage = "Unable to update withdrawal as no withdrawal was found matching the provided withdrawal ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated withdrawal matching the provided withdrawal ID: " + transactionId;
        Transaction successResponseData = this.withdrawalService.updateWithdrawal(transactionId, exceptionMessage, transactionUpdateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/withdrawals/{transactionId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to delete withdrawal as no withdrawal was found matching the provided withdrawal ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully deleted withdrawal matching the provided withdrawal ID: " + transactionId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.withdrawalService.deleteWithdrawal(transactionId, exceptionMessage);
        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
