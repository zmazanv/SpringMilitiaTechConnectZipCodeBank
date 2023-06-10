package bank.connect.tech.service;

import bank.connect.tech.model.Account;
import bank.connect.tech.models.Deposit;
import bank.connect.tech.repositories.DepositRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepositService {
    private final DepositRepository depositRepository;
    private CrudRepository accountRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public Deposit createDeposit(Deposit deposit, Long customerID, Account account) {
        if (account.getCustomer().equals(customerID)) {
            return depositRepository.save(deposit);
        } else {
            return null;
        }
    }

    public Deposit getDepositById(Long id) {
        return depositRepository.findById(id).orElse(null);
    }

    public List<Deposit> getAllDeposits() {
        return (List<Deposit>) depositRepository.findAll();
    }

    public Deposit updateDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    public void deleteDeposit(Long id) {
        depositRepository.deleteById(id);
    }

    public List<Deposit> getAllDepositsForAccount(Long accountId) {
        return depositRepository.findAllByAccountId(accountId);
    }

    public Deposit createDeposit(Long accountId, Deposit deposit) {
        Account account = (Account) accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            deposit.setAccount(account);
            return depositRepository.save(deposit);
        }
        return null;
    }


}







