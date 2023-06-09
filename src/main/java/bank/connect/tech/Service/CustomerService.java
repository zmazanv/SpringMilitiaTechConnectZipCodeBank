package bank.connect.tech.Service;

import bank.connect.tech.model.Customer;
import bank.connect.tech.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

   @Autowired
    private CustomerRepo customerRepo;

    //Creating a customer
    public void addCustomer (Customer customer){
        customerRepo.save(customer);
    }

    //Get all customers
    public Iterable<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    //Get customer by Id
    public Optional<Customer> getCustomerById (Long id){
        return customerRepo.findById(id);
    }

    //Update the existing customer
    public void updateCustomer (Long id, Customer customer){
        customer.setId(id);
        customerRepo.save(customer);
    }
    //Get customer that owns the specified accounts
  //  public Customer getCustomerByAccounts (Set<Account> accounts)


}

