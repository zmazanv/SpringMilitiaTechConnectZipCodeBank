package bank.connect.tech.controller.transaction;

import bank.connect.tech.TechConnectZipCodeBankApplication;
import bank.connect.tech.dto.create.transaction.P2PTransactionCreateDTO;
import bank.connect.tech.dto.update.TransactionUpdateDTO;
import bank.connect.tech.model.Transaction;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.transaction.P2PTransactionService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class P2PTransactionController {

    @Autowired
    private P2PTransactionService p2PTransactionService;


    @GetMapping("/p2p") //
    public ResponseEntity<?> getAllP2PTransactions() {
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched all P2P transactions";
        Iterable<Transaction> successResponseData = this.p2PTransactionService.getAllP2PTransactions();
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/accounts/{accountId}/p2p")
    public ResponseEntity<?> getAllP2PTransactionsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch P2P transactions as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched P2P transactions matching the provided account ID: " + accountId;
        Iterable<Transaction> successResponseData = this.p2PTransactionService.getAllP2PTransactionsByAccountId(accountId, exceptionMessage);
        SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/p2p/{transactionId}")
    public ResponseEntity<?> getP2PTransactionById(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to fetch P2P transaction as no deposit was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched P2P transaction matching the provided transaction ID: " + transactionId;
        Transaction successResponseData = this.p2PTransactionService.getP2PTransactionById(transactionId, exceptionMessage);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/accounts/{senderAccountId}/p2p")
    public ResponseEntity<?>createP2PTransaction(@PathVariable Long senderAccountId, @Valid @RequestBody P2PTransactionCreateDTO p2PTransactionCreateDTO) {
        String senderExceptionMessage = "Unable to create new P2P transaction as no account was found matching the provided sender account ID: " + senderAccountId;
        String receiverExceptionMessage = "Unable to create new P2P transaction as no account was found matching the provided receiver account ID: " + p2PTransactionCreateDTO.getReceiverAccountId();

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new P2P transaction from sender account, ID: " + senderAccountId + ", to receiver account, ID: " + p2PTransactionCreateDTO.getReceiverAccountId();
        Transaction successResponseData = this.p2PTransactionService.createP2PTransaction(senderAccountId, senderExceptionMessage, receiverExceptionMessage, p2PTransactionCreateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/p2p/{transactionId}")
    public ResponseEntity<?> updateP2PTransaction(@PathVariable Long transactionId, @Valid @RequestBody TransactionUpdateDTO transactionUpdateDTO) {
        String exceptionMessage = "Unable to update P2P transaction as no P2P transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated P2P transaction matching the provided transaction ID: " + transactionId;
        Transaction successResponseData = this.p2PTransactionService.updateP2PTransaction(transactionId, exceptionMessage, transactionUpdateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/p2p/{transactionId}")
    public ResponseEntity<?> cancelP2PTransaction(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to cancel P2P Transaction as no P2P Transaction was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully cancelled P2P Transaction matching the provided transaction ID: " + transactionId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.p2PTransactionService.cancelP2PTransaction(transactionId, exceptionMessage);
        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
