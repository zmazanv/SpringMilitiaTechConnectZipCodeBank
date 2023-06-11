package bank.connect.tech.repository;

import bank.connect.tech.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId")
    Iterable<Account> findAllAccountsByCustomerId (Long customerId);
}
