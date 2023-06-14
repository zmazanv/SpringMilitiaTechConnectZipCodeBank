package bank.connect.tech.service;

import bank.connect.tech.dto.create.TransactionCreateDTO;
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
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

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

    protected void verifyCustomer (Long customerId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    protected void verifyTransaction(Long transactionId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.transactionRepository.existsById(transactionId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }


    public Transaction getTransactionById(Long transactionId, String exceptionMessage) {
        this.verifyTransaction(transactionId, exceptionMessage);
        return this.transactionRepository.findById(transactionId).get();
    }

    public Transaction createTransaction(Long accountId, String exceptionMessage, TransactionCreateDTO transactionCreateDTO) {
        this.verifyAccount(accountId, exceptionMessage);
        LocalDate today = LocalDate.now();
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.fromString(transactionCreateDTO.getType()));
        transaction.setStatus(TransactionStatus.fromString(transactionCreateDTO.getStatus()));
        transaction.setMedium(TransactionMedium.fromString(transactionCreateDTO.getMedium()));
        transaction.setAmount(transactionCreateDTO.getAmount());
        transaction.setDescription(transactionCreateDTO.getDescription());
        transaction.setTransactionDate(today);
        transaction.setAccount(this.accountRepository.findById(accountId).get());
        if(transaction.getType() == TransactionType.DEPOSIT){
            updateBalance(accountId,exceptionMessage,transaction.getAmount(),0.00);
        } else if ( transaction.getType() == TransactionType.WITHDRAWAL){
                updateBalance(accountId,exceptionMessage,0.0,transaction.getAmount());
            }
            return this.transactionRepository.save(transaction);
        }

    public Transaction updateTransaction(Long transactionId, String exceptionMessage, TransactionUpdateDTO transactionUpdateDTO) {
        this.verifyTransaction(transactionId, exceptionMessage);
        Transaction transactionToUpdate = this.transactionRepository.findById(transactionId).get();
        if (!(Objects.isNull(transactionUpdateDTO.getStatus())) && !(transactionUpdateDTO.getStatus().isBlank())) {
            transactionToUpdate.setStatus(TransactionStatus.fromString(transactionUpdateDTO.getStatus()));
        }
        if (!(Objects.isNull(transactionUpdateDTO.getDescription())) && !(transactionUpdateDTO.getDescription().isBlank())) {
            transactionToUpdate.setDescription(transactionUpdateDTO.getDescription());
        }
        if(transactionToUpdate.getType() == TransactionType.DEPOSIT){
            updateBalance(transactionId,exceptionMessage,transactionToUpdate.getAmount(),0.00);
        } else if ( transactionToUpdate.getType() == TransactionType.WITHDRAWAL){
            updateBalance(transactionId,exceptionMessage,0.0,transactionToUpdate.getAmount());
        }
        if (transactionToUpdate.getStatus() == TransactionStatus.CANCELLED) {
            if (transactionToUpdate.getType() == TransactionType.DEPOSIT) {
                updateBalance(transactionId, exceptionMessage,0.00, transactionToUpdate.getAmount());
            } else if (transactionToUpdate.getType() == TransactionType.WITHDRAWAL) {
                updateBalance(transactionId, exceptionMessage, transactionToUpdate.getAmount(), 0.00);
            }
        }

        return this.transactionRepository.save(transactionToUpdate);
    }

    public Transaction updateBalance (Long transactionId, String exceptionMessage, Double deposit, Double withdrawl){
        this.verifyTransaction(transactionId, exceptionMessage);
        Transaction updateBalance = this.transactionRepository.findById(transactionId).get();
        if(updateBalance != null){
            Double currentBalance = updateBalance.getAmount();

            currentBalance += deposit;
            currentBalance -= withdrawl;

            updateBalance.setAmount(currentBalance);
        }
        return this.transactionRepository.save(updateBalance);
    }


    // P2P
    public Transaction peerToPeer(Long accountId, Double deposit, Double withdrawal, Double balance,String exceptionMessage){
       Account senderAccount = accountRepository.findById(accountId);
    }

    // Find the accountID we are going to send monies from
    // we need to get that accounts balance
    // we need the account ID from person we are sending too
    // how much we are sending
    // click send
    // to subract from existing account new amount ==
    // add to the new account we sent monies too new amount.


    public void deleteTransaction(Long transactionId, String exceptionMessage) {
        this.verifyTransaction(transactionId, exceptionMessage);
        this.transactionRepository.deleteById(transactionId);
    }

    public Iterable<Transaction> getAllTransactionsByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        return this.transactionRepository.findAllTransactionsByAccountId(accountId);
    }
}
