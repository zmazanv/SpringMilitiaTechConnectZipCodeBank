package bank.connect.tech.controller;
import bank.connect.tech.model.Account;
import bank.connect.tech.model.Customer;
import bank.connect.tech.service.AccountService;
import bank.connect.tech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;


    @PostMapping("/customers")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        Account account = new Account();
        account.setCustomer(customer); // Set the customer for the account
        accountService.createAccount(customer.getId(), account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById (@PathVariable Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Void> updateCustomer (@PathVariable Long id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

  //  @GetMapping("/accounts/{accountId}/customer")
       // public ResponseEntity<?> getCustomerByAccount (@PathVariable)
}
