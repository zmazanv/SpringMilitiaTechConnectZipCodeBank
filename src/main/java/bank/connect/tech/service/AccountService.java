package bank.connect.tech.service;

import bank.connect.tech.dto.create.AccountCreateDTO;
import bank.connect.tech.dto.update.AccountUpdateDTO;
import bank.connect.tech.response.exception.ResourceNotFoundException;
import bank.connect.tech.model.Account;
import bank.connect.tech.model.enumeration.AccountType;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.CustomerRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;


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


    public Iterable<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Account getAccountById(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        return this.accountRepository.findById(accountId).get();
    }

    public Account createAccount(Long customerId, String exceptionMessage, AccountCreateDTO accountCreateDTO) {
        this.verifyCustomer(customerId, exceptionMessage);
        Account account = new Account();
        account.setType(AccountType.fromString(accountCreateDTO.getType().trim()));
        account.setBalance(0.0);
        if (!(Objects.isNull(accountCreateDTO.getNickname())) && !(accountCreateDTO.getNickname().isBlank())) {
            account.setNickname(accountCreateDTO.getNickname().trim());
        }
        account.setRewards(0);
        account.setCustomer(this.customerRepository.findById(customerId).get());
        return this.accountRepository.save(account);
    }

    public Account updateAccount(Long accountId, String exceptionMessage, AccountUpdateDTO accountUpdateDTO) {
        this.verifyAccount(accountId, exceptionMessage);
        Account accountToUpdate = this.accountRepository.findById(accountId).get();
        //if (!(Objects.isNull(accountUpdateDTO.getBalance()))) {
        //    accountToUpdate.setBalance(accountUpdateDTO.getBalance());
        //}
        if (!(Objects.isNull(accountUpdateDTO.getNickname())) && !(accountUpdateDTO.getNickname().isBlank())) {
            accountToUpdate.setNickname(accountUpdateDTO.getNickname().trim());
        }
        if (!(Objects.isNull(accountUpdateDTO.getRewards()))) {
            accountToUpdate.setRewards(accountUpdateDTO.getRewards());
        }
        return this.accountRepository.save(accountToUpdate);
    }

    public void deleteAccount(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        this.accountRepository.deleteById(accountId);
    }

    public Iterable<Account> getAllAccountsByCustomerId (Long customerId, String exceptionMessage) {
        this.verifyCustomer(customerId, exceptionMessage);
        return this.accountRepository.findAllAccountsByCustomerId(customerId);
    }
}
