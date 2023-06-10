package bank.connect.tech.service;

import bank.connect.tech.model.Account;
import bank.connect.tech.models.Deposit;
import bank.connect.tech.repositories.DepositRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepositService {
    private final DepositRepository depositRepository;

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
        // Implement the logic to retrieve all deposits for a specific account
        // Example: return depositRepository.findAllByAccountId(accountId);
        return Collections.emptyList();
    }

    public Deposit createDeposit(Long accountId, Deposit deposit) {
        return null;
    }
}







