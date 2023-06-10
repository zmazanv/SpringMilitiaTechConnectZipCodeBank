package bank.connect.tech.repositories;

import bank.connect.tech.models.Deposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {

    List<Deposit> findAllByAccountId(Long accountId);
}

