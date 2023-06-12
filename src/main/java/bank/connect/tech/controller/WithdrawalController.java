package bank.connect.tech.controller;

import bank.connect.tech.model.Withdrawal;
import bank.connect.tech.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/accounts")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @GetMapping("/{accountId}/withdrawals")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalsForAccount(@PathVariable("accountId") Long accountId) {
        List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalsForAccount(accountId);
        return ResponseEntity.ok().body(withdrawals);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Withdrawal> getWithdrawalById(@PathVariable("withdrawalId") Long withdrawalId) {
        Withdrawal withdrawal = withdrawalService.getWithdrawalById(withdrawalId);
        if (withdrawal != null) {
            return ResponseEntity.ok().body(withdrawal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{accountId}/withdrawals")
    public ResponseEntity<Withdrawal> createWithdrawal(@PathVariable("accountId") Long accountId, @RequestBody Withdrawal withdrawal) {
        Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(accountId, withdrawal);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWithdrawal);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Withdrawal> updateWithdrawal(@PathVariable("withdrawalId") Long withdrawalId, @RequestBody Withdrawal withdrawal) {
        Withdrawal updatedWithdrawal = withdrawalService.updateWithdrawal(withdrawalId, withdrawal);
        if (updatedWithdrawal != null) {
            return ResponseEntity.ok().body(updatedWithdrawal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable("withdrawalId") Long withdrawalId) {
        boolean deleted = withdrawalService.deleteWithdrawal(withdrawalId);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
