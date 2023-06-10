package bank.connect.tech.repository;

import bank.connect.tech.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
    public Iterable<Bill> findAllBillsByAccountId(Long accountId);
}
