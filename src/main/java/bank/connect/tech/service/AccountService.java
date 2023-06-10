package bank.connect.tech.service;

import bank.connect.tech.exception.ResourceNotFoundException;
import bank.connect.tech.model.Account;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    protected void verifyAccount (Long accountId) throws ResourceNotFoundException {
        if(!(this.accountRepository.existsById(accountId))) {
            throw (new ResourceNotFoundException("Error fetching accounts"));
        }
    }


    public Iterable<Account> getAllAccounts() {

        return this.accountRepository.findAll();
    }

    public Account getAccountById(Long accountId) {
        this.verifyAccount(accountId);
        return this.accountRepository.findById(accountId).get();
    }

    public void createAccount(Long customerId, Account account) {
        this.customerService.verifyCustomer(customerId);
        account.setCustomer(this.customerRepository.findById(customerId).get());
        this.accountRepository.save(account);
    }

    public Iterable<Account> getAllAccountsByCustomerId (Long customerId) {
        this.customerService.verifyCustomer(customerId);
        return this.accountRepository.findAllAccountsByCustomerId(customerId);
    }

    public void updateAccount(Long accountId, Account account) {
        this.verifyAccount(accountId);
        account.setId(accountId);
        this.accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        this.verifyAccount(accountId);
        this.accountRepository.deleteById(accountId);
    }
}
