package bank.connect.tech.repository;

import bank.connect.tech.model.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE b.account.id = :accountId")
    public Iterable<Bill> findAllBillsByAccountId(Long accountId);
    @Query("SELECT b FROM Bill b WHERE b.account.customer.id = :customerId")
    public Iterable<Bill> findAllBillsByCustomerId(Long customerId);
}
