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
public class WithdrawalService {

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

    protected void verifyWithdrawal(Long transactionId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.transactionRepository.existsById(transactionId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
        if(this.transactionRepository.findById(transactionId).get().getType() != TransactionType.WITHDRAWAL) {
            throw (new ResourceNotFoundException("The transaction, ID: " + transactionId + ", does not appear to be a withdrawal, rather it is a " + this.transactionRepository.findById(transactionId).get().getType().toString().toLowerCase()));
        }
    }


    public Iterable<Transaction> getAllWithdrawals() {
        List<Transaction> allWithdrawals = new ArrayList<>();
        for (Transaction withdrawal : this.transactionRepository.findAll()) {
            if (withdrawal.getType() == TransactionType.WITHDRAWAL) {
                allWithdrawals.add(withdrawal);
            }
        }
        return allWithdrawals;
    }

    public Transaction getWithdrawalById(Long transactionId, String exceptionMessage) {
        this.verifyWithdrawal(transactionId, exceptionMessage);
        return this.transactionRepository.findById(transactionId).get();
    }

    public Transaction createWithdrawal(Long accountId, String exceptionMessage, TransactionCreateDTO transactionCreateDTO) {
        this.verifyAccount(accountId, exceptionMessage);
        LocalDate today = LocalDate.now();
        Transaction withdrawal = new Transaction();
        withdrawal.setType(TransactionType.WITHDRAWAL);
        withdrawal.setStatus(TransactionStatus.fromString(transactionCreateDTO.getStatus()));
        withdrawal.setMedium(TransactionMedium.fromString(transactionCreateDTO.getMedium()));
        withdrawal.setAmount(transactionCreateDTO.getAmount());
        withdrawal.setDescription(transactionCreateDTO.getDescription());
        withdrawal.setTransactionDate(today);
        withdrawal.setAccount(this.accountRepository.findById(accountId).get());
        return this.transactionRepository.save(withdrawal);
    }

    public Transaction updateWithdrawal(Long transactionId, String exceptionMessage, TransactionUpdateDTO transactionUpdateDTO) {
        this.verifyWithdrawal(transactionId, exceptionMessage);
        Transaction withdrawalToUpdate = this.transactionRepository.findById(transactionId).get();
        if (!(Objects.isNull(transactionUpdateDTO.getStatus())) && !(transactionUpdateDTO.getStatus().isBlank())) {
            withdrawalToUpdate.setStatus(TransactionStatus.fromString(transactionUpdateDTO.getStatus()));
        }
        if (!(Objects.isNull(transactionUpdateDTO.getDescription())) && !(transactionUpdateDTO.getDescription().isBlank())) {
            withdrawalToUpdate.setDescription(transactionUpdateDTO.getDescription());
        }
        return this.transactionRepository.save(withdrawalToUpdate);
    }

    public void deleteWithdrawal(Long transactionId, String exceptionMessage) {
        this.verifyWithdrawal(transactionId, exceptionMessage);
        this.transactionRepository.deleteById(transactionId);
    }

    public Iterable<Transaction> getAllWithdrawalsByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        List<Transaction> allWithdrawalsByAccountId = new ArrayList<>();
        for (Transaction withdrawal : this.transactionRepository.findAllTransactionsByAccountId(accountId)) {
            if (withdrawal.getType() == TransactionType.WITHDRAWAL) {
                allWithdrawalsByAccountId.add(withdrawal);
            }
        }
        return allWithdrawalsByAccountId;
    }
}
