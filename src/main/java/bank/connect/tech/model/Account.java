package bank.connect.tech.model;

import bank.connect.tech.model.enumeration.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private AccountType type;
    @Column(name = "nickname")
    @JsonProperty("nickname")
    private String nickname;
    @Column(name = "rewards")
    @JsonProperty("rewards")
    private Integer rewards;
    @Column(name = "balance")
    @JsonProperty("balance")
    private Double balance;
    @ManyToOne(optional = false)
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

    @JsonIgnore
    public Customer getCustomer() {return this.customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    @JsonProperty("customer_id")
    public Long getCustomerId() {
        return this.customer != null ? this.customer.getId() : null;
    }
}