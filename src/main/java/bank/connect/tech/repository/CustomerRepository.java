package bank.connect.tech.repository;

import bank.connect.tech.model.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	@Query("SELECT a.customer FROM Account a WHERE a.id = ?1")
	Customer findCustomerByAccountId(Long accountId);
}
