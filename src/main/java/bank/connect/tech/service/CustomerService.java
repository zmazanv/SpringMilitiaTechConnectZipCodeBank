package bank.connect.tech.service;

import bank.connect.tech.exception.ResourceNotFoundException;
import bank.connect.tech.model.Account;
import bank.connect.tech.model.Customer;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

   @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;



    protected void verifyCustomer (Long customerId) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new RuntimeException("Error fetching customers accounts"));
        }
    }

    //Creating a customer
    public void addCustomer (Customer customer){
        customerRepository.save(customer);
    }

    //Get all customers
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //Get customer by id
    public Optional<Customer> getCustomerById (Long id){
        return customerRepository.findById(id);
    }

    //Update the existing customer
    public void updateCustomer (Long id, Customer customer){
        customer.setId(id);
        customerRepository.save(customer);
    }
    //Get customer that owns the specified accounts
     public Customer getCustomerByAccountId (Long accountId){
       return accountRepository.findCustomerByAccountId(accountId);
     }


}

