package bank.connect.tech.service;

import bank.connect.tech.model.Deposit;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepositService {
    private final Map<Long, List<Deposit>> accountDeposits;

    public DepositService() {
        this.accountDeposits = new HashMap<>();
    }

    public List<Deposit> getAllDepositsForAccount(Long accountId) {
        List<Deposit> deposits = accountDeposits.get(accountId);
        if (deposits == null) {
            throw new NotFoundException("Account not found");
        }
        return deposits;
    }

    public Deposit getDepositById(Long depositId) {
        for (List<Deposit> deposits : accountDeposits.values()) {
            for (Deposit deposit : deposits) {
                if (deposit.getId().equals(depositId)) {
                    return deposit;
                }
            }
        }
        throw new NotFoundException("Error fetching deposit with id: " + depositId);
    }

    public Deposit createDeposit(Long accountId, Deposit deposit) {
        List<Deposit> deposits = accountDeposits.get(accountId);
        if (deposits == null) {
            throw new NotFoundException("Account not found");
        }

        deposit.setId(generateDepositId());
        deposits.add(deposit);
        return deposit;
    }

//    public Deposit updateDeposit(Deposit deposit) {
//        for (List<Deposit> deposits : accountDeposits.values()) {
//            for (Deposit d : deposits) {
//                if (d.getId().equals(deposit.getId())) {
//                    d.setMedium(deposit.getMedium());
//                    d.setTransactionDate(deposit.getTransactionDate());
//                    d.setAmount(deposit.getAmount());
//                    d.setDescription(deposit.getDescription());
//                    d.setStatus(deposit.getStatus());
//                    d.setPayeeId(deposit.getPayeeId());
//                    d.setType(deposit.getType());
//                    return d;
//                }
//            }
//        }
//        throw new NotFoundException("Deposit ID does not exist");
//    }

    public void deleteDeposit(Long depositId) {
        for (List<Deposit> deposits : accountDeposits.values()) {
            deposits.removeIf(deposit -> deposit.getId().equals(depositId));
        }
    }

    private Long generateDepositId() {
        // Generate a unique ID for the deposit (you can replace this with your own logic)
        // This is just a simple example
        return System.currentTimeMillis();
    }

    private static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
