package bank.connect.tech.controller.transaction;

import bank.connect.tech.TechConnectZipCodeBankApplication;
import bank.connect.tech.dto.update.TransactionUpdateDTO;
import bank.connect.tech.model.Account;
import bank.connect.tech.model.Transaction;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.transaction.TransactionService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/transactions") //
    public ResponseEntity<?> getAllTransactions() {
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched all transactions";
        Iterable<Transaction> successResponseData = this.transactionService.getAllTransactions();
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/accounts/{accountId}/transactions") // TODO: DONE
    public ResponseEntity<?> getAllTransactionsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch transactions as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched transactions matching the provided account ID: " + accountId;
        Iterable<Transaction> successResponseData = this.transactionService.getAllTransactionsByAccountId(accountId, exceptionMessage);
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/transactions/{transactionId}") // TODO: DONE
    public ResponseEntity<?> getTransactionById(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to fetch transaction as no transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched transaction matching the provided transaction ID: " + transactionId;
        Transaction successResponseData = this.transactionService.getTransactionById(transactionId, exceptionMessage);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    //@PostMapping("/accounts/{accountId}/transactions") // TODO: DONE
    //public ResponseEntity<?>createTransaction(@PathVariable Long accountId, @Valid @RequestBody TransactionCreateDTO transactionCreateDTO) {
    //    String exceptionMessage = "Unable to create new transaction as no account was found matching the provided account ID: " + accountId;

    //    int successResponseCode = HttpStatus.CREATED.value();
    //    String successResponseMessage = "Successfully created new transaction for account with ID: " + accountId;
    //    Transaction successResponseData = this.transactionService.createTransaction(accountId, exceptionMessage, transactionCreateDTO);
    //    SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

    //    TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
    //    return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    //}

    @PutMapping("/transactions/{transactionId}") // TODO: DONE
    public ResponseEntity<?> updateTransaction(@PathVariable Long transactionId, @Valid @RequestBody TransactionUpdateDTO transactionUpdateDTO) {
        String exceptionMessage = "Unable to update transaction as no transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated transaction matching the provided transaction ID: " + transactionId;
        Transaction successResponseData = this.transactionService.updateTransaction(transactionId, exceptionMessage, transactionUpdateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/transactions/{transactionId}") // TODO: DONE
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to delete transaction as no transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully deleted transaction matching the provided transaction ID: " + transactionId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.transactionService.deleteTransaction(transactionId, exceptionMessage);
        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/transaction/P2P")
    public ResponseEntity<Transaction> peerToPeerTransaction(@RequestParam Long senderAccountId, @RequestParam Long receiverAccountId, @RequestParam Long balance) {
        String exceptionMessage = "Unable to make transaction to receiver as there was no receiver found matching the provided Receiver Account ID: " + receiverAccountId;

        int successResponseCode = HttpStatus.OK.value();
        String successfulSendMessage = "Successfully sent monies to the provided receiver account ID: " + receiverAccountId;
        String successResponseMessage = "Successfully received monies from sender :" + senderAccountId;
        //Transaction successResponseData = this.transactionService.updateTransaction(senderAccountId,exceptionMessage,transactionService.updateBalance(balance));
        //SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);
        return (new ResponseEntity<>(HttpStatus.OK));
    }

    @GetMapping("/transactions")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        String exceptionMessage = "Unable to fetch transactions.";

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched transactions.";
        Iterable<Transaction> successResponseData = this.transactionService.getAllTransactions();
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return ResponseEntity.ok(successResponseData);
    }
}
