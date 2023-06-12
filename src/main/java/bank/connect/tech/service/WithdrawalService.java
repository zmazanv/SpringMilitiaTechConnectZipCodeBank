package bank.connect.tech.service;


import bank.connect.tech.model.Withdrawal;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WithdrawalService {
    private final Map<Long, List<Withdrawal>> accountWithdrawals;

    public WithdrawalService() {
        this.accountWithdrawals = new HashMap<>();
    }

    public List<Withdrawal> getAllWithdrawalsForAccount(Long accountId) {
        List<Withdrawal> withdrawals = accountWithdrawals.get(accountId);
        if (withdrawals == null) {
            throw new NotFoundException("Account not found");
        }
        return withdrawals;
    }

    public Withdrawal getWithdrawalById(Long withdrawalId) {
        for (List<Withdrawal> withdrawals : accountWithdrawals.values()) {
            for (Withdrawal withdrawal : withdrawals) {
                if (withdrawal.getId().equals(withdrawalId)) {
                    return withdrawal;
                }
            }
        }
        throw new NotFoundException("Error fetching withdrawal with id: " + withdrawalId);
    }

    public Withdrawal createWithdrawal(Long accountId, Withdrawal withdrawal) {
        List<Withdrawal> withdrawals = accountWithdrawals.get(accountId);
        if (withdrawals == null) {
            throw new NotFoundException("Account not found");
        }

        withdrawal.setId(generateWithdrawalId());
        withdrawals.add(withdrawal);
        return withdrawal;
    }

    public Withdrawal updateWithdrawal(Long withdrawalId, Withdrawal withdrawal) {
        for (List<Withdrawal> withdrawals : accountWithdrawals.values()) {
            for (Withdrawal w : withdrawals) {
                if (w.getId().equals(withdrawal.getId())) {
                    w.setMedium(withdrawal.getMedium());
                    w.setTransactionDate(withdrawal.getTransactionDate());
                    w.setAmount(withdrawal.getAmount());
                    w.setDescription(withdrawal.getDescription());
                    w.setStatus(withdrawal.getStatus());
                    w.setPayerId(withdrawal.getPayerId());
                    w.setType(withdrawal.getType());
                    return w;
                }
            }
        }
        throw new NotFoundException("Withdrawal ID does not exist");
    }

    public void deleteWithdrawal(Long withdrawalId) {
        for (List<Withdrawal> withdrawals : accountWithdrawals.values()) {
            withdrawals.removeIf(withdrawal -> withdrawal.getId().equals(withdrawalId));
        }
    }

    private Long generateWithdrawalId() {
        // Generate a unique ID for the withdrawal (you can replace this with your own logic)
        // This is just a simple example
        return System.currentTimeMillis();
    }

    private static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
