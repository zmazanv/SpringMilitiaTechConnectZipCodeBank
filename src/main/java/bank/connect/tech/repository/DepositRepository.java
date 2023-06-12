package bank.connect.tech.repository;

import bank.connect.tech.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Customer, Long> {}
