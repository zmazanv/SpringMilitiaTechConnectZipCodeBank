package bank.connect.tech.controller;

import bank.connect.tech.model.Customer;
import bank.connect.tech.model.Deposit;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<SuccessResponse<List<Deposit>>> getAllDepositsForAccount(@PathVariable("accountId") Long accountId) {
        List<Deposit> deposits = depositService.getAllDepositsForAccount(accountId);
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched all deposits";
        SuccessResponse<List<Deposit>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, deposits);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<SuccessResponse<Deposit>> getDepositById(@PathVariable("depositId") Long depositId) {
        Deposit deposit = depositService.getDepositById(depositId);
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched deposit with ID: " + depositId;
        SuccessResponse<Deposit> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, deposit);
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/accounts/{accountId}/deposits")
    public Deposit createDeposit(@PathVariable("accountId") Long accountId, @RequestBody Deposit deposit) {
        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new customer";
        Deposit successResponseData = this.depositService.createDeposit(accountId,deposit);
        SuccessResponse<Deposit> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);
        return depositService.createDeposit(accountId, deposit);
    }

//    @PutMapping("/deposits/{depositId}")
//    public Deposit updateDeposit(@PathVariable("depositId") Long depositId, @RequestBody Deposit deposit) {
//        String exceptionMessage = "Unable to update customer as no customer was found matching the provided customer ID: " + depositId;
//
//        int successResponseCode = HttpStatus.OK.value();
//        String successResponseMessage = "Successfully updated customer matching the provided customer ID: " + depositId;
//        //Customer successResponseData = this.depositService.updateDeposit(depositId, exceptionMessage);
//        //SuccessResponse<Customer> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);
//
//        deposit.setId(depositId);
//        //return depositService.updateDeposit(deposit);
//    }

    @DeleteMapping("/deposits/{depositId}")
    public void deleteDeposit(@PathVariable("depositId") Long depositId) {

        depositService.deleteDeposit(depositId);
    }
}
