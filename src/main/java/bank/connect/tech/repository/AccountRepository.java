package bank.connect.tech.repository;
import bank.connect.tech.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository <Account,Long> {
    List<Account> findAccountById(Long accountId);
}
