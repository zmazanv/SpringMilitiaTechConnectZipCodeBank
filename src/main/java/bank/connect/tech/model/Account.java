package bank.connect.tech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AccountType type ;
    private String nickname;
    private Integer rewards;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public AccountType getType() {return this.type;}
    public void setType(AccountType type) {this.type = type;}

    public String getNickname() {return this.nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}

    public Integer getRewards() {return this.rewards;}
    public void setRewards(Integer rewards) {this.rewards = rewards;}

    public Double getBalance() {return this.balance;}
    public void setBalance(Double balance) {this.balance = balance;}

    public Customer getCustomer() {return this.customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}
}
