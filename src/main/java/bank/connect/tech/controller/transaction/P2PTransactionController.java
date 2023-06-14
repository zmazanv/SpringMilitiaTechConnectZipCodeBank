package bank.connect.tech.controller.transaction;

import bank.connect.tech.TechConnectZipCodeBankApplication;
import bank.connect.tech.dto.create.transaction.P2PCreateDTO;
import bank.connect.tech.dto.update.TransactionUpdateDTO;
import bank.connect.tech.model.Transaction;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.transaction.P2PService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class P2PController {

    @Autowired
    private P2PService p2PService;


    //@GetMapping("/p2p") //
    //public ResponseEntity<?> getAllDeposits() {
    //    int successResponseCode = HttpStatus.OK.value();
    //    String successResponseMessage = "Successfully fetched all deposits";
    //    Iterable<Transaction> successResponseData = this.depositService.getAllDeposits();
    //    SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

    //    TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
    //    return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    //}

    //@GetMapping("/accounts/{accountId}/p2p")
    //public ResponseEntity<?> getAllDepositsByAccountId(@PathVariable Long accountId) {
    //    String exceptionMessage = "Unable to fetch deposits as no account was found matching the provided account ID: " + accountId;

    //    int successResponseCode = HttpStatus.OK.value();
    //    String successResponseMessage = "Successfully fetched deposits matching the provided account ID: " + accountId;
    //    Iterable<Transaction> successResponseData = this.depositService.getAllDepositsByAccountId(accountId, exceptionMessage);
    //    SuccessResponse<Iterable<Transaction>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

    //    TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
    //    return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    //}

    //@GetMapping("/p2p/{transactionId}")
    //public ResponseEntity<?> getDepositById(@PathVariable Long transactionId) {
    //    String exceptionMessage = "Unable to fetch deposit as no deposit was found matching the provided transaction ID: " + transactionId;

    //    int successResponseCode = HttpStatus.OK.value();
    //    String successResponseMessage = "Successfully fetched deposit matching the provided transaction ID: " + transactionId;
    //    Transaction successResponseData = this.depositService.getDepositById(transactionId, exceptionMessage);
    //    SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

    //    TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
    //    return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    //}

    @PostMapping("/accounts/{senderAccountId}/p2p")
    public ResponseEntity<?>createDeposit(@PathVariable Long senderAccountId, @Valid @RequestBody P2PCreateDTO p2PCreateDTO) {
        String senderExceptionMessage = "Unable to create new P2P transaction as no account was found matching the provided sender account ID: " + senderAccountId;
        String receiverExceptionMessage = "Unable to create new P2P transaction as no account was found matching the provided receiver account ID: " + p2PCreateDTO.getReceiverAccountId();

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new P2P transaction from sender account, ID: " + senderAccountId + ", to receiver account, ID: " + p2PCreateDTO.getReceiverAccountId();
        Transaction successResponseData = this.p2PService.createP2PTransaction(senderAccountId, senderExceptionMessage, receiverExceptionMessage, p2PCreateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/p2p/{transactionId}")
    public ResponseEntity<?> updateDeposit(@PathVariable Long transactionId, @Valid @RequestBody TransactionUpdateDTO transactionUpdateDTO) {
        String exceptionMessage = "Unable to update deposit as no deposit was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated deposit matching the provided transaction ID: " + transactionId;
        Transaction successResponseData = this.depositService.updateDeposit(transactionId, exceptionMessage, transactionUpdateDTO);
        SuccessResponse<Transaction> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/p2p/{transactionId}")
    public ResponseEntity<?> cancelDeposit(@PathVariable Long transactionId) {
        String exceptionMessage = "Unable to cancel deposit as no deposit was found matching the provided transaction ID: " + transactionId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully cancelled deposit matching the provided transaction ID: " + transactionId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.depositService.cancelDeposit(transactionId, exceptionMessage);
        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
