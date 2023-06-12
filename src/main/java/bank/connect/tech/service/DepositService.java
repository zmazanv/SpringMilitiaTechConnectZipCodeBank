package bank.connect.tech.service;

import bank.connect.tech.dto.create.BillCreateDTO;
import bank.connect.tech.dto.create.DepositCreateDTO;
import bank.connect.tech.dto.update.BillUpdateDTO;
import bank.connect.tech.model.Bill;
import bank.connect.tech.model.Deposit;
import bank.connect.tech.model.enumeration.BillStatus;
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

    public Bill updateBill(Long billId, String exceptionMessage, BillUpdateDTO billUpdateDTO) {
        this.verifyBill(billId, exceptionMessage);
        Bill billToUpdate = this.billRepository.findById(billId).get();
        if (!(Objects.isNull(billUpdateDTO.getStatus())) && !(billUpdateDTO.getStatus().isBlank())) {
            billToUpdate.setBillStatus(BillStatus.fromString(billUpdateDTO.getStatus()));
        }
        if (!(Objects.isNull(billUpdateDTO.getPayee())) && !(billUpdateDTO.getPayee().isBlank())) {
            billToUpdate.setPayee(billUpdateDTO.getPayee());
        }
        if (!(Objects.isNull(billUpdateDTO.getNickname())) && !(billUpdateDTO.getNickname().isBlank())) {
            billToUpdate.setNickname(billUpdateDTO.getNickname());
        }
        if (!(Objects.isNull(billUpdateDTO.getPaymentDate()))) {
            billToUpdate.setPaymentDate(billUpdateDTO.getPaymentDate());
        }
        if (!(Objects.isNull(billUpdateDTO.getRecurringDate()))) {
            billToUpdate.setRecurringDate(billUpdateDTO.getRecurringDate());
            LocalDate today = LocalDate.now();
            LocalDate nextRecurringDate = LocalDate.of(today.getYear(), today.getMonth(), billUpdateDTO.getRecurringDate());
            if (nextRecurringDate.isBefore(today)) {
                nextRecurringDate = nextRecurringDate.plusMonths(1);
            }
            billToUpdate.setUpcomingPaymentDate(nextRecurringDate);
        }
        //if (billUpdateDTO.getPaymentAmount() != null) {
        if (!(Objects.isNull(billUpdateDTO.getPaymentAmount()))) {
            billToUpdate.setPaymentAmount(billUpdateDTO.getPaymentAmount());
        }
        return this.billRepository.save(billToUpdate);
    }

    public void deleteBill(Long billId, String exceptionMessage) {
        this.verifyBill(billId, exceptionMessage);
        this.billRepository.deleteById(billId);
    }

    public Iterable<Bill> getAllBillsByAccountId(Long accountId, String exceptionMessage) {
        this.verifyAccount(accountId, exceptionMessage);
        return this.billRepository.findAllBillsByAccountId(accountId);
    }

    public Iterable<Bill> getAllBillsByCustomerId(Long customerId, String exceptionMessage) {
        this.verifyCustomer(customerId, exceptionMessage);
        return this.billRepository.findAllBillsByCustomerId(customerId);
    }
}
