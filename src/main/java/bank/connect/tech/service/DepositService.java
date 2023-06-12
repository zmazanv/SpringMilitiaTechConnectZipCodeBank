package bank.connect.tech.service;

import bank.connect.tech.dto.create.DepositCreateDTO;
import bank.connect.tech.dto.update.DepositUpdateDTO;
import bank.connect.tech.model.Deposit;
import bank.connect.tech.model.enumeration.TransactionMedium;
import bank.connect.tech.model.enumeration.TransactionStatus;
import bank.connect.tech.model.enumeration.TransactionType;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.BillRepository;
import bank.connect.tech.repository.CustomerRepository;
import bank.connect.tech.repository.DepositRepository;
import bank.connect.tech.response.exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;


    protected void verifyAccount(Long accountId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.accountRepository.existsById(accountId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyBill(Long billId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.billRepository.existsById(billId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }

    protected void verifyCustomer (Long customerId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyDeposit(Long depositId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.depositRepository.existsById(depositId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }


    public Deposit getDepositById(Long depositId, String exceptionMessage) {
        this.verifyDeposit(depositId, exceptionMessage);
        return this.depositRepository.findById(depositId).get();
    }

    public Deposit createDeposit(Long accountId, String exceptionMessage, DepositCreateDTO depositCreateDTO) {
        this.verifyAccount(accountId, exceptionMessage);
        LocalDate today = LocalDate.now();
        Deposit deposit = new Deposit();
        deposit.setType(TransactionType.fromString(depositCreateDTO.getType()));
        deposit.setStatus(TransactionStatus.fromString(depositCreateDTO.getStatus()));
        deposit.setMedium(TransactionMedium.fromString(depositCreateDTO.getMedium()));
        deposit.setAmount(depositCreateDTO.getAmount());
        deposit.setDescription(depositCreateDTO.getDescription());
        deposit.setTransactionDate(today);
        deposit.setAccount(this.accountRepository.findById(accountId).get());
        return this.depositRepository.save(deposit);
    }

    public Deposit updateDeposit(Long depositId, String exceptionMessage, DepositUpdateDTO depositUpdateDTO) {
        this.verifyDeposit(depositId, exceptionMessage);
        Deposit depositToUpdate = this.depositRepository.findById(depositId).get();
        if (!(Objects.isNull(depositUpdateDTO.getStatus())) && !(depositUpdateDTO.getStatus().isBlank())) {
            depositToUpdate.setStatus(TransactionStatus.fromString(depositUpdateDTO.getStatus()));
        }
        if (!(Objects.isNull(depositUpdateDTO.getDescription())) && !(depositUpdateDTO.getDescription().isBlank())) {
            depositToUpdate.setDescription(depositUpdateDTO.getDescription());
        }
        return this.depositRepository.save(depositToUpdate);
    }

    public void deleteDeposit(Long depositId, String exceptionMessage) {
        this.verifyDeposit(depositId, exceptionMessage);
        this.depositRepository.deleteById(depositId);
    }

    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        return this.depositRepository.findAllDepositsByAccountId(accountId);
    }
}
