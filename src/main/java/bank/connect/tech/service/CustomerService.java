package bank.connect.tech.service;

import bank.connect.tech.model.Customer;
import bank.connect.tech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

   @Autowired
    private CustomerRepository customerRepository;


    protected void verifyCustomer (Long customerId) throws RuntimeException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new RuntimeException("The Customer: " + customerId + " does not exist. Please try again."));
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
    /* public Customer getCustomerByAccounts (Set<Account> accounts){
        for (Account account : accounts){
            Customer customer = account.getCustomer();
            if (customer != null){
                return customer;
            }
        }
        return null;
     }*/


}

