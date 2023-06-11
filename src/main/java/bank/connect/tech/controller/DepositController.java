package bank.connect.tech.controller;

import bank.connect.tech.model.Deposit;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;


    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDepositById (@PathVariable Long accountId){
        String exceptionMessage = "Account not found";

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched customer of account with ID: " + accountId;
        Deposit successResponseData = this.depositService.getDepositById(accountId, exceptionMessage);
        SuccessResponse<Deposit> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
