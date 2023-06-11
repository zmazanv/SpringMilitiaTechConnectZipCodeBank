package bank.connect.tech.controller;

import bank.connect.tech.model.Customer;
import bank.connect.tech.response.SuccessResponse;
import bank.connect.tech.service.CustomerService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch customer as no account was found matching the provided account ID: " + accountId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched customer of account with ID: " + accountId;
        Customer successResponseData = this.customerService.getCustomerByAccountId(accountId, exceptionMessage);
        SuccessResponse<Customer> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping ("/customers")
    public ResponseEntity<?> createCustomer (@Valid @RequestBody Customer customer){
        int successResponseCode = HttpStatus.CREATED.value();
        String successResponseMessage = "Successfully created new customer";
        Customer successResponseData = this.customerService.createCustomer(customer);
        SuccessResponse<Customer> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched all customers";
        Iterable<Customer> successResponseData = this.customerService.getAllCustomers();
        SuccessResponse<Iterable<Customer>> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getCustomerById (@PathVariable Long customerId){
        String exceptionMessage = "Unable to fetch customer as no customer was found matching the provided customer ID: " + customerId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully fetched customer matching the provided customer ID: " + customerId;
        Customer successResponseData = this.customerService.getCustomerById(customerId, exceptionMessage);
        SuccessResponse<Customer> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> updateCustomer (@PathVariable Long customerId, @Valid @RequestBody Customer customer){
        String exceptionMessage = "Unable to update customer as no customer was found matching the provided customer ID: " + customerId;

        int successResponseCode = HttpStatus.OK.value();
        String successResponseMessage = "Successfully updated customer matching the provided customer ID: " + customerId;
        Customer successResponseData = this.customerService.updateCustomer(customerId, exceptionMessage, customer);
        SuccessResponse<Customer> successResponse = new SuccessResponse<>(successResponseCode, successResponseMessage, successResponseData);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }
}
