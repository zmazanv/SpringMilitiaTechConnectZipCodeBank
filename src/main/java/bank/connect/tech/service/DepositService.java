package bank.connect.tech.service;

@Service
public class DepositService {
    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public Deposit createDeposit(Deposit deposit) {
        // Add any additional logic, validations, or transformations if needed
        return depositRepository.save(deposit);
    }

    public Deposit getDepositById(Long id) {
        return depositRepository.findById(id).orElse(null);
    }

    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    public Deposit updateDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    public void deleteDeposit(Long id) {
        depositRepository.deleteById(id);
    }
}






