package bank.connect.tech.repository;

import bank.connect.tech.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
    Iterable<Transaction> findAllTransactionsByAccountId(Long accountId);
}
