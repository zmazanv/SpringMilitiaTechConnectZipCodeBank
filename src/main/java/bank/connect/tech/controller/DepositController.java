package bank.connect.tech.controller;

import bank.connect.tech.model.Account;
import bank.connect.tech.model.Customer;
import bank.connect.tech.models.Deposit;
import bank.connect.tech.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<List<Deposit>> getAllDepositsForAccount(@PathVariable("accountId") Long accountId) {
        try {
            List<Deposit> deposits = depositService.getAllDepositsForAccount(accountId);
            return ResponseEntity.ok(deposits);
        } catch (DepositService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable("depositId") Long depositId) {
        try {
            Deposit deposit = depositService.getDepositById(depositId);
            return ResponseEntity.ok(deposit);
        } catch (DepositService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<Deposit> createDeposit(@PathVariable("accountId") Long accountId, @RequestBody Deposit deposit) {
        try {
            Deposit createdDeposit = depositService.createDeposit(accountId, deposit);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDeposit);
        } catch (DepositService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<String> updateDeposit(@PathVariable("depositId") Long depositId, @RequestBody Deposit deposit) {
        try {
            deposit.setId(depositId);
            depositService.updateDeposit(deposit);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Accepted deposit modification");
        } catch (DepositService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable("depositId") Long depositId) {
        try {
            depositService.deleteDeposit(depositId);
            return ResponseEntity.noContent().build();
        } catch (DepositService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}