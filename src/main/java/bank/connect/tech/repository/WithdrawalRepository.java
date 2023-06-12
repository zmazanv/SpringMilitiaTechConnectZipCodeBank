package bank.connect.tech.repository;

import bank.connect.tech.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawalRepository extends CrudRepository<Customer, Long> {

}
