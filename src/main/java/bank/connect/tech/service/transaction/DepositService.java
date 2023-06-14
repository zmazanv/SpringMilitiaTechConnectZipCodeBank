package bank.connect.tech.service.transaction;

import bank.connect.tech.dto.create.TransactionCreateDTO;
import bank.connect.tech.dto.update.TransactionUpdateDTO;
import bank.connect.tech.model.Transaction;
import bank.connect.tech.model.enumeration.TransactionMedium;
import bank.connect.tech.model.enumeration.TransactionStatus;
import bank.connect.tech.model.enumeration.TransactionType;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.BillRepository;
import bank.connect.tech.repository.CustomerRepository;
import bank.connect.tech.repository.TransactionRepository;
import bank.connect.tech.response.exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private TransactionRepository transactionRepository;


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

    protected void verifyCustomer(Long customerId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyTransaction(Long transactionId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.transactionRepository.existsById(transactionId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyDeposit(Long transactionId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.transactionRepository.existsById(transactionId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
        if(this.transactionRepository.findById(transactionId).get().getType() != TransactionType.DEPOSIT) {
            throw (new ResourceNotFoundException("The transaction, ID: " + transactionId + ", does not appear to be a deposit, rather it is a " + this.transactionRepository.findById(transactionId).get().getType().toString().toLowerCase()));
        }
    }


    public Iterable<Transaction> getAllDeposits() {
        List<Transaction> allDeposits = new ArrayList<>();
        for (Transaction deposit : this.transactionRepository.findAll()) {
            if (deposit.getType() == TransactionType.DEPOSIT) {
                allDeposits.add(deposit);
            }
        }
        return allDeposits;
    }

    public Transaction getDepositById(Long transactionId, String exceptionMessage) {
        this.verifyDeposit(transactionId, exceptionMessage);
        return this.transactionRepository.findById(transactionId).get();
    }

    public Transaction createDeposit(Long accountId, String exceptionMessage, TransactionCreateDTO transactionCreateDTO) {
        this.verifyAccount(accountId, exceptionMessage);
        LocalDate today = LocalDate.now();
        Transaction deposit = new Transaction();
        deposit.setType(TransactionType.DEPOSIT);
        deposit.setStatus(TransactionStatus.fromString(transactionCreateDTO.getStatus()));
        deposit.setMedium(TransactionMedium.fromString(transactionCreateDTO.getMedium()));
        deposit.setAmount(transactionCreateDTO.getAmount());
        deposit.setDescription(transactionCreateDTO.getDescription());
        deposit.setTransactionDate(today);
        deposit.setAccount(this.accountRepository.findById(accountId).get());
        return this.transactionRepository.save(deposit);
    }

    public Transaction updateDeposit(Long transactionId, String exceptionMessage, TransactionUpdateDTO transactionUpdateDTO) {
        this.verifyDeposit(transactionId, exceptionMessage);
        Transaction depositToUpdate = this.transactionRepository.findById(transactionId).get();
        if (!(Objects.isNull(transactionUpdateDTO.getStatus())) && !(transactionUpdateDTO.getStatus().isBlank())) {
            depositToUpdate.setStatus(TransactionStatus.fromString(transactionUpdateDTO.getStatus()));
        }
        if (!(Objects.isNull(transactionUpdateDTO.getDescription())) && !(transactionUpdateDTO.getDescription().isBlank())) {
            depositToUpdate.setDescription(transactionUpdateDTO.getDescription());
        }
        return this.transactionRepository.save(depositToUpdate);
    }

    public void deleteDeposit(Long transactionId, String exceptionMessage) {
        this.verifyDeposit(transactionId, exceptionMessage);
        this.transactionRepository.deleteById(transactionId);
    }

    public Iterable<Transaction> getAllDepositsByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        List<Transaction> allDepositsByAccountId = new ArrayList<>();
        for (Transaction deposit : this.transactionRepository.findAllTransactionsByAccountId(accountId)) {
            if (deposit.getType() == TransactionType.DEPOSIT) {
                allDepositsByAccountId.add(deposit);
            }
        }
        return allDepositsByAccountId;
    }
}
