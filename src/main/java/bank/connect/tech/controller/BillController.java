package bank.connect.tech.controller;

import bank.connect.tech.TechConnectZipCodeBankApplication;
import bank.connect.tech.dto.create.BillCreateDTO;
import bank.connect.tech.dto.update.BillUpdateDTO;
import bank.connect.tech.model.Bill;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.BillService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {

    @Autowired
    private BillService billService;


    @GetMapping("/accounts/{accountId}/bills") // TODO: DONE
    public ResponseEntity<?> getAllBillsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch bills as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched bills matching the provided account ID: " + accountId;
        Iterable<Bill> successResponseData = this.billService.getAllBillsByAccountId(accountId, exceptionMessage);
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/customers/{customerId}/bills") // TODO: DONE
    public ResponseEntity<?> getAllBillsByCustomerId(@PathVariable Long customerId) {
        String exceptionMessage = "Unable to fetch bills as no customer was found matching the provided customer ID: " + customerId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched bills matching the provided customer ID: " + customerId;
        Iterable<Bill> successResponseData = this.billService.getAllBillsByCustomerId(customerId, exceptionMessage);
        SuccessResponse<Iterable<Bill>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/bills/{billId}") // TODO: DONE
    public ResponseEntity<?> getBillWithId(@PathVariable Long billId) {
        String exceptionMessage = "Unable to fetch bill as no bill was found matching the provided bill ID: " + billId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched bill matching the provided bill ID: " + billId;
        Bill successResponseData = this.billService.getBillById(billId, exceptionMessage);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/accounts/{accountId}/bills") // TODO: DONE
    public ResponseEntity<?>createBill(@PathVariable Long accountId, @Valid @RequestBody BillCreateDTO billCreateDTO) {
        String exceptionMessage = "Unable to create new bill as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new bill for account with ID: " + accountId;
        Bill successResponseData = this.billService.createBill(accountId, exceptionMessage, billCreateDTO);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/bills/{billId}") // TODO: DONE
    public ResponseEntity<?> updateBill(@PathVariable Long billId, @Valid @RequestBody BillUpdateDTO billUpdateDTO) {
        String exceptionMessage = "Unable to update bill as no bill was found matching the provided bill ID: " + billId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated bill matching the provided bill ID: " + billId;
        Bill successResponseData = this.billService.updateBill(billId, exceptionMessage, billUpdateDTO);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @DeleteMapping("/bills/{billId}") // TODO: DONE
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        String exceptionMessage = "Unable to delete bill as no bill was found matching the provided bill ID: " + billId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully deleted bill matching the provided bill ID: " + billId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, null);

        this.billService.deleteBill(billId, exceptionMessage);
        TechConnectZipCodeBankApplication.logger.info(successResponseMessage);
        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
