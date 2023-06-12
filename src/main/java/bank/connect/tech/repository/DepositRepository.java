package bank.connect.tech.repository;

import bank.connect.tech.model.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, Long> {

    @Query("SELECT d FROM Deposit d WHERE d.account.id = :accountId")
    Iterable<Deposit> findAllDepositsByAccountId(Long accountId);
}
