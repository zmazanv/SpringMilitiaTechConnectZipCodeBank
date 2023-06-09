package bank.connect.tech.service;

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


    protected void verifyAccount (Long accountId) throws RuntimeException {
        if(!(this.accountRepository.existsById(accountId))) {
            throw (new RuntimeException("The Account: " + accountId + " does not exist. Please try again."));
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

    public void updateAccount(Long accountId, Account account) {
        this.verifyAccount(accountId);
        account.setId(accountId);
        this.accountRepository.save(account);
        //Optional<Account> optionalAccount = this.accountRepository.findById(accountId); // here I called all the accounts that exist and placed them in optionalAccount.
        //if (optionalAccount.isPresent()) { //here I am making sure that I do have that account in order to update it.
        //    Account existingAccount = optionalAccount.get(); // here I am calling those accounts that exist into my if loop
        //    existingAccount.setType(account.getType());     // all of these set new values to the account and can be individually updated
        //    existingAccount.setNickname(account.getNickname());
        //    existingAccount.setRewards(account.getRewards());
        //    existingAccount.setBalance(account.getBalance());
        //    this.accountRepository.save(existingAccount); // here I save all of the updates at the end of the loop.
        //}
    }

    public void deleteAccount(Long accountId) {
        this.verifyAccount(accountId);
        this.accountRepository.deleteById(accountId);
    }
}
