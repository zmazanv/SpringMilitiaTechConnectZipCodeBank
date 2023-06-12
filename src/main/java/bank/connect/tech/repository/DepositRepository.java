package bank.connect.tech.repository;

import bank.connect.tech.model.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, Long> {}
