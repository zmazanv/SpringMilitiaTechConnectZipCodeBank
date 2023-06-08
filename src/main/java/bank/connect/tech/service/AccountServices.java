package bank.connect.tech.service;
import bank.connect.tech.domain.Account;
import bank.connect.tech.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountServices {

    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account>getAccounts(){
        return accountRepository.findAll();
    }

    public Optional<Account> getAccount(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public void updateAccount(Account account, Long id){
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }

//    protected void verifyAccount (Long accountId){
//        if(!(this.accountRepository.existsById(accountId))){
//            throw (new Exception("The Account : " + accountId + "does not exist please try again"));
//        }
//    }
}
