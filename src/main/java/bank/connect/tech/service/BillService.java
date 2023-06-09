package bank.connect.tech.service;

import bank.connect.tech.dto.create.BillCreateDTO;
import bank.connect.tech.dto.update.BillUpdateDTO;
import bank.connect.tech.model.enumeration.BillStatus;
import bank.connect.tech.repository.CustomerRepository;
import bank.connect.tech.response.exception.ResourceNotFoundException;
import bank.connect.tech.model.Bill;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.BillRepository;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;


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


    public Bill getBillById(Long billId, String exceptionMessage) {
        this.verifyBill(billId, exceptionMessage);
        return this.billRepository.findById(billId).get();
    }

    public Bill createBill(Long accountId, String exceptionMessage, BillCreateDTO billCreateDTO) {
        this.verifyAccount(accountId, exceptionMessage);
        Bill bill = new Bill();
        LocalDate today = LocalDate.now();
        LocalDate nextRecurringDate = LocalDate.of(today.getYear(), today.getMonth(), billCreateDTO.getRecurringDate());
        bill.setStatus(BillStatus.fromString(billCreateDTO.getStatus().trim()));
        bill.setPayee(billCreateDTO.getPayee().trim());
        if (!(Objects.isNull(billCreateDTO.getNickname())) && !(billCreateDTO.getNickname().isBlank())) {
            bill.setNickname(billCreateDTO.getNickname().trim());
        }
        bill.setCreationDate(today);
        bill.setPaymentDate(billCreateDTO.getPaymentDate());
        bill.setRecurringDate(billCreateDTO.getRecurringDate());
        if (nextRecurringDate.isBefore(today)) {
            nextRecurringDate = nextRecurringDate.plusMonths(1);
        }
        bill.setUpcomingPaymentDate(nextRecurringDate);
        bill.setPaymentAmount(billCreateDTO.getPaymentAmount());
        bill.setAccount(this.accountRepository.findById(accountId).get());
        return this.billRepository.save(bill);
    }

    public Bill updateBill(Long billId, String exceptionMessage, BillUpdateDTO billUpdateDTO) {
        this.verifyBill(billId, exceptionMessage);
        Bill billToUpdate = this.billRepository.findById(billId).get();
        if (!(Objects.isNull(billUpdateDTO.getStatus())) && !(billUpdateDTO.getStatus().isBlank())) {
            billToUpdate.setStatus(BillStatus.fromString(billUpdateDTO.getStatus().trim()));
        }
        if (!(Objects.isNull(billUpdateDTO.getPayee())) && !(billUpdateDTO.getPayee().isBlank())) {
            billToUpdate.setPayee(billUpdateDTO.getPayee().trim());
        }
        if (!(Objects.isNull(billUpdateDTO.getNickname())) && !(billUpdateDTO.getNickname().isBlank())) {
            billToUpdate.setNickname(billUpdateDTO.getNickname().trim());
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
