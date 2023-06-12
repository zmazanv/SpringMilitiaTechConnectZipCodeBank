package bank.connect.tech.controller;

import bank.connect.tech.dto.create.DepositCreateDTO;
import bank.connect.tech.dto.update.DepositUpdateDTO;
import bank.connect.tech.model.Deposit;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.DepositService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;


    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDepositsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch deposits as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched deposits matching the provided account ID: " + accountId;
        Iterable<Deposit> successResponseData = this.depositService.getAllDepositsByAccountId(accountId, exceptionMessage);
        SuccessResponse<Iterable<Deposit>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId) {
        String exceptionMessage = "Unable to fetch deposit as no deposit was found matching the provided deposit ID: " + depositId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched deposit matching the provided deposit ID: " + depositId;
        Deposit successResponseData = this.depositService.getDepositById(depositId, exceptionMessage);
        SuccessResponse<Deposit> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?>createDeposit(@PathVariable Long accountId, @Valid @RequestBody DepositCreateDTO depositCreateDTO) {
        String exceptionMessage = "Unable to create new deposit as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new deposit for account with ID: " + accountId;
        Deposit successResponseData = this.depositService.createDeposit(accountId, exceptionMessage, depositCreateDTO);
        SuccessResponse<Deposit> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositId, @Valid @RequestBody DepositUpdateDTO depositUpdateDTO) {
        String exceptionMessage = "Unable to update deposit as no deposit was found matching the provided deposit ID: " + depositId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated deposit matching the provided deposit ID: " + depositId;
        Deposit successResponseData = this.depositService.updateDeposit(depositId, exceptionMessage, depositUpdateDTO);
        SuccessResponse<Deposit> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) {
        String exceptionMessage = "Unable to delete deposit as no deposit was found matching the provided deposit ID: " + depositId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully deleted deposit matching the provided deposit ID: " + depositId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.depositService.deleteDeposit(depositId, exceptionMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
