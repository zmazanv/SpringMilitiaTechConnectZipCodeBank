package bank.connect.tech.service.transaction;

import bank.connect.tech.dto.create.transaction.P2PTransactionCreateDTO;
import bank.connect.tech.dto.update.TransactionUpdateDTO;
import bank.connect.tech.model.Account;
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
public class P2PTransactionService {

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

    protected void verifyP2PTransaction(Long transactionId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.transactionRepository.existsById(transactionId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
        if(this.transactionRepository.findById(transactionId).get().getType() != TransactionType.P2P) {
            throw (new ResourceNotFoundException("The transaction, ID: " + transactionId + ", does not appear to be a P2P transaction, rather it is a " + this.transactionRepository.findById(transactionId).get().getType().toString().toLowerCase()));
        }
    }


    public Iterable<Transaction> getAllP2PTransactions() {
        List<Transaction> allP2PTransactions = new ArrayList<>();
        for (Transaction p2PTransaction : this.transactionRepository.findAll()) {
            if (p2PTransaction.getType() == TransactionType.P2P) {
                allP2PTransactions.add(p2PTransaction);
            }
        }
        return allP2PTransactions;
    }

    public Transaction getP2PTransactionById(Long transactionId, String exceptionMessage) {
        this.verifyDeposit(transactionId, exceptionMessage);
        return this.transactionRepository.findById(transactionId).get();
    }

    public Transaction createP2PTransaction(Long senderAccountId, String senderExceptionMessage, String receiverExceptionMessage, P2PTransactionCreateDTO p2PTransactionCreateDTO) {
        this.verifyAccount(senderAccountId, senderExceptionMessage);
        this.verifyAccount(p2PTransactionCreateDTO.getReceiverAccountId(), receiverExceptionMessage);
        LocalDate today = LocalDate.now();
        Transaction p2PTransaction = new Transaction();
        p2PTransaction.setType(TransactionType.P2P);
        p2PTransaction.setStatus(TransactionStatus.COMPLETED);
        p2PTransaction.setMedium(TransactionMedium.fromString(p2PTransactionCreateDTO.getMedium()));
        p2PTransaction.setAmount(p2PTransactionCreateDTO.getAmount());
        if (!(Objects.isNull(p2PTransactionCreateDTO.getDescription())) && !(p2PTransactionCreateDTO.getDescription().isBlank())) {
            p2PTransaction.setDescription(p2PTransactionCreateDTO.getDescription().trim());
        }
        p2PTransaction.setTransactionDate(today);
        p2PTransaction.setAccount(this.accountRepository.findById(senderAccountId).get());
        p2PTransaction.setReceiverAccount(this.accountRepository.findById(p2PTransactionCreateDTO.getReceiverAccountId()).get());
        Account senderAccountToDecrease = this.accountRepository.findById(senderAccountId).get();
        Account receiverAccountToIncrease = this.accountRepository.findById(p2PTransactionCreateDTO.getReceiverAccountId()).get();
        senderAccountToDecrease.setBalance(senderAccountToDecrease.getBalance() - p2PTransaction.getAmount());
        this.accountRepository.save(senderAccountToDecrease);
        receiverAccountToIncrease.setBalance(receiverAccountToIncrease.getBalance() + p2PTransaction.getAmount());
        this.accountRepository.save(receiverAccountToIncrease);
        return this.transactionRepository.save(p2PTransaction);
    }

    public Transaction updateP2PTransaction(Long transactionId, String exceptionMessage, TransactionUpdateDTO transactionUpdateDTO) {
        this.verifyP2PTransaction(transactionId, exceptionMessage);
        Transaction p2pTransactionToUpdate = this.transactionRepository.findById(transactionId).get();
        if (!(Objects.isNull(transactionUpdateDTO.getDescription())) && !(transactionUpdateDTO.getDescription().isBlank())) {
            p2pTransactionToUpdate.setDescription(transactionUpdateDTO.getDescription().trim());
        }
        return this.transactionRepository.save(p2pTransactionToUpdate);
    }

    public void cancelP2PTransaction(Long transactionId, String exceptionMessage) {
        this.verifyP2PTransaction(transactionId, exceptionMessage);
        Transaction p2PTransactionToCancel = this.transactionRepository.findById(transactionId).get();
        Account senderAccountToCorrect = p2PTransactionToCancel.getAccount();
        Account receiverAccountToCorrect = p2PTransactionToCancel.getReceiverAccount();
        senderAccountToCorrect.setBalance(senderAccountToCorrect.getBalance() + p2PTransactionToCancel.getAmount());
        this.accountRepository.save(senderAccountToCorrect);
        this.accountRepository.save(senderAccountToCorrect);
        receiverAccountToCorrect.setBalance(receiverAccountToCorrect.getBalance() - p2PTransactionToCancel.getAmount());
        this.accountRepository.save(receiverAccountToCorrect);
        this.accountRepository.save(receiverAccountToCorrect);
        p2PTransactionToCancel.setStatus(TransactionStatus.CANCELLED);
        this.transactionRepository.save(p2PTransactionToCancel);
    }

    public Iterable<Transaction> getAllP2PTransactionsByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        List<Transaction> allP2PTransactionsByAccountId = new ArrayList<>();
        for (Transaction p2PTransaction : this.transactionRepository.findAllTransactionsByAccountId(accountId)) {
            if (p2PTransaction.getType() == TransactionType.P2P) {
                allP2PTransactionsByAccountId.add(p2PTransaction);
            }
        }
        return allP2PTransactionsByAccountId;
    }
}
