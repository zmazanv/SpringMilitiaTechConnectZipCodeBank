package bank.connect.tech.service;

import bank.connect.tech.model.Account;
import bank.connect.tech.model.Address;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.response.exception.ResourceNotFoundException;
import bank.connect.tech.model.Customer;
import bank.connect.tech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;


    protected void verifyAccount(Long accountId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.accountRepository.existsById(accountId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyCustomer (Long customerId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }


    //Get all customers
    public Iterable<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    //Get customer by id
    public Customer getCustomerById(Long customerId, String exceptionMessage) {
        this.verifyCustomer(customerId, exceptionMessage);
        return this.customerRepository.findById(customerId).get();
    }

    //Creating a customer
    public Customer createCustomer(Customer customer) {
        for (Address address : customer.getAddresses()) {
            address.setCustomer(customer);
        }
        return this.customerRepository.save(customer);
    }

    //Update the existing customer
    public Customer updateCustomer(Long customerId, String exceptionMessage, Customer customer){
        this.verifyCustomer(customerId, exceptionMessage);
        Customer customerToUpdate = this.customerRepository.findById(customerId).get();
        if (!(customer.getFirstName().isBlank())) {
            customerToUpdate.setFirstName(customer.getFirstName());
        }
        if (!(customer.getLastName().isBlank())) {
            customerToUpdate.setLastName(customer.getLastName());
        }
        if (!(customer.getAddresses().isEmpty()) && customer.getAddresses() != null) {
            for (Address address : customerToUpdate.getAddresses()) {
                address.setCustomer(null);
            }
            customerToUpdate.getAddresses().clear();
            for (Address address : customer.getAddresses()) {
                address.setCustomer(customerToUpdate);
                customerToUpdate.getAddresses().add(address);
            }
        }
        return this.customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(Long customerId, String exceptionMessage) {
        this.verifyCustomer(customerId, exceptionMessage);
        this.customerRepository.deleteById(customerId);
    }

    public Customer getCustomerByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        return this.customerRepository.findCustomerByAccountId(accountId);
    }
}

