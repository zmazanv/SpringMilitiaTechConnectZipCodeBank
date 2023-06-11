package bank.connect.tech.controller;

import bank.connect.tech.model.Account;
import bank.connect.tech.model.Customer;
import bank.connect.tech.models.Deposit;
import bank.connect.tech.service.DepositService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/accounts/{accountId}/deposits")
    public List<Deposit> getAllDepositsForAccount(@PathVariable("accountId") Long accountId) {
        return depositService.getAllDepositsForAccount(accountId);
    }

    @GetMapping("/deposits/{depositId}")
    public Deposit getDepositById(@PathVariable("depositId") Long depositId) {
        return depositService.getDepositById(depositId);
    }

    @PostMapping("/accounts/{accountId}/deposits")
    public Deposit createDeposit(@PathVariable("accountId") Long accountId, @RequestBody Deposit deposit) {
        return depositService.createDeposit(accountId, deposit);
    }

    @PutMapping("/deposits/{depositId}")
    public Deposit updateDeposit(@PathVariable("depositId") Long depositId, @RequestBody Deposit deposit) {
        deposit.setId(depositId);
        return depositService.updateDeposit(deposit);
    }

    @DeleteMapping("/deposits/{depositId}")
    public void deleteDeposit(@PathVariable("depositId") Long depositId) {
        depositService.deleteDeposit(depositId);
    }