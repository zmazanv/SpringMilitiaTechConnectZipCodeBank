package bank.connect.tech.controller;

import bank.connect.tech.Service.CustomerService;
import bank.connect.tech.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


   /* @PostMapping ("/customers")
    public ResponseEntity<Void> createCustomer (@RequestBody Customer customer){

    }*/
}
