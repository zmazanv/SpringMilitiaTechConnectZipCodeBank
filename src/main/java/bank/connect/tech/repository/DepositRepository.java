package bank.connect.tech.repository;

import bank.connect.tech.model.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository <Deposit, Long> {
}
