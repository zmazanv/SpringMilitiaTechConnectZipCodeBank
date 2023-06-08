package bank.connect.tech.controller;
import bank.connect.tech.domain.Account;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.service.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping
    public Iterable<Account> getAllAccounts (){
        return accountServices.getAccounts();
    }
    @GetMapping("/{accountId}")
    public Optional<Account> getAllAccountById(@PathVariable Long accountId){
        return accountServices.getAccount(accountId);
    }

    @PostMapping()
    public void createAccount(@Valid @RequestBody Account account){
        accountServices.createAccount(account);
    }
    @PutMapping("/accountId")
    public void updateAccount(Account account, Long id) { // this method takes 2 parameters : the account that I want to update and its ID.
        Optional<Account> optionalAccount = accountRepository.findById(id); // here I called all the accounts that exist and placed them in optionalAccount.
        if (optionalAccount.isPresent()) { //here I am making sure that I do have that account in order to update it.
            Account existingAccount = optionalAccount.get(); // here I am calling those accounts that exist into my if loop
            existingAccount.setType(account.getType());     // all of these set new values to the account and can be individually updated
            existingAccount.setNickname(account.getNickname());
            existingAccount.setRewards(account.getRewards());
            existingAccount.setBalance(account.getBalance());
            accountRepository.save(existingAccount); // here I save all of the updates at the end of the loop.
        }
    }
    @DeleteMapping("/accountId") // just deleting - but I should check if it exists kind of like above:
    public void deleteAccount (@PathVariable Long accountId){
        Optional<Account> accountCheck = accountRepository.findById(accountId);
        if (accountCheck.isPresent()){
            accountServices.deleteAccount(accountId);
        }
    }
}
