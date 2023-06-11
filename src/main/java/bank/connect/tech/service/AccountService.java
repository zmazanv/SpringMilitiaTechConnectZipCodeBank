package bank.connect.tech.service;

import bank.connect.tech.dto.AccountDTO;
import bank.connect.tech.model.Customer;
import bank.connect.tech.response.exception.ResourceNotFoundException;
import bank.connect.tech.model.Account;
import bank.connect.tech.model.AccountType;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    protected void verifyAccount(Long accountId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.accountRepository.existsById(accountId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }


    public Iterable<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Account getAccountById(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        return this.accountRepository.findById(accountId).get();
    }

    public Account createAccount(Long customerId, String exceptionMessage, AccountDTO accountDTO) {
        this.customerService.verifyCustomer(customerId, exceptionMessage);
        Account account = new Account();
        account.setAccountType(AccountType.fromString(accountDTO.getType()));
        account.setBalance(0.0);
        account.setNickname(accountDTO.getNickname());
        account.setRewards(0);
        account.setCustomer(this.customerRepository.findById(customerId).get());
        return this.accountRepository.save(account);
    }

    public Account updateAccount(Long accountId, String exceptionMessage, Account account) {
        this.verifyAccount(accountId, exceptionMessage);
        Account accountToUpdate = this.accountRepository.findById(accountId).get();
        if (account.getBalance() != null) {
            accountToUpdate.setBalance(account.getBalance());
        }
        if (account.getNickname() != null && !(Objects.equals(account.getNickname(), ""))) {
            accountToUpdate.setNickname(account.getNickname());
        }
        if (account.getRewards() != null) {
            accountToUpdate.setRewards(account.getRewards());
        }
        return this.accountRepository.save(accountToUpdate);
    }

    public void deleteAccount(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        this.accountRepository.deleteById(accountId);
    }

    public Iterable<Account> getAllAccountsByCustomerId (Long customerId, String exceptionMessage) {
        this.customerService.verifyCustomer(customerId, exceptionMessage);
        return this.accountRepository.findAllAccountsByCustomerId(customerId);
    }
}
