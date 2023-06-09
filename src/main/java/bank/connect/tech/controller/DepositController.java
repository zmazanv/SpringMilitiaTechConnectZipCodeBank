package bank.connect.tech.controller;

@RestController
@RequestMapping("/deposits")
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping
    public Deposit createDeposit(@RequestBody Deposit deposit) {
        return depositService.createDeposit(deposit);
    }

    @GetMapping("/{id}")
    public Deposit getDepositById(@PathVariable("id") Long id) {
        return depositService.getDepositById(id);
    }

    @GetMapping
    public List<Deposit> getAllDeposits() {
        return depositService.getAllDeposits();
    }

    @PutMapping("/{id}")
    public Deposit updateDeposit(@PathVariable("id") Long id, @RequestBody Deposit deposit) {
        deposit.setId(id);
        return depositService.updateDeposit(deposit);
    }

    @DeleteMapping("/{id}")
    public void deleteDeposit(@PathVariable("id") Long id) {
        depositService.deleteDeposit(id);
    }
}
