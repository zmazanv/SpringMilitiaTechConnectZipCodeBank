package bank.connect.tech.repositories;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    // You can add custom query methods if needed
}