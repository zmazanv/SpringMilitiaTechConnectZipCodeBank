package bank.connect.tech.service;
import bank.connect.tech.domain.Account;
import bank.connect.tech.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account>getAccounts(){
        return this.accountRepository.findAll();
    }

    public Optional<Account> getAccount(Long accountId) {
        return this.accountRepository.findById(accountId);
    }

    public void createAccount(Account account){

        this.accountRepository.save(account);
    }

    protected void verifyAccount (Long accountId) throws Exception {
        if(!(this.accountRepository.existsById(accountId))){
            throw (new Exception("The Account : " + accountId + "does not exist please try again"));
        }
    }
    public void updateAccount(Account account, Long id){
        Optional<Account> optionalAccount = this.accountRepository.findById(id); // here I called all the accounts that exist and placed them in optionalAccount.
        if (optionalAccount.isPresent()) { //here I am making sure that I do have that account in order to update it.
            Account existingAccount = optionalAccount.get(); // here I am calling those accounts that exist into my if loop
            existingAccount.setType(account.getType());     // all of these set new values to the account and can be individually updated
            existingAccount.setNickname(account.getNickname());
            existingAccount.setRewards(account.getRewards());
            existingAccount.setBalance(account.getBalance());
            this.accountRepository.save(existingAccount); // here I save all of the updates at the end of the loop.
        }
    }
    public void deleteAccount(Long accountId) {
        Optional<Account> accountCheck = this.accountRepository.findById(accountId);
        if (accountCheck.isPresent()) {
            this.accountRepository.delete(accountCheck.get());
        }
    }
}
