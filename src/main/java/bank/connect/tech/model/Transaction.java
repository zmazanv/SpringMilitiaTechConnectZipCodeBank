package bank.connect.tech.model;

import bank.connect.tech.model.enumeration.TransactionMedium;
import bank.connect.tech.model.enumeration.TransactionStatus;
import bank.connect.tech.model.enumeration.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private TransactionType type;
    @Column(name = "transaction_date")
    @JsonProperty("transaction_date")
    private LocalDate transactionDate;
    @Column(name = "status")
    @JsonProperty("status")
    private TransactionStatus status;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "medium")
    @JsonProperty("medium")
    private TransactionMedium medium;
    @Column(name = "amount")
    @JsonProperty("amount")
    private Double amount;
    @Column(name = "description")
    @JsonProperty("description")
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;


    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public TransactionType getType() {return this.type;}
    public void setType(TransactionType type) {this.type = type;}

    public LocalDate getTransactionDate() {return this.transactionDate;}
    public void setTransactionDate(LocalDate transactionDate) {this.transactionDate = transactionDate;}

    public TransactionStatus getStatus() {return this.status;}
    public void setStatus(TransactionStatus status) {this.status = status;}

    public TransactionMedium getMedium() {return this.medium;}
    public void setMedium(TransactionMedium medium) {this.medium = medium;}

    public Double getAmount() {return this.amount;}
    public void setAmount(Double amount) {this.amount = amount;}

    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}

    @JsonIgnore
    public Account getAccount() {return this.account;}
    public void setAccount(Account account) {this.account = account;}

    @JsonProperty("payee_id")
    public Long getAccountId() {
        return this.account != null ? this.account.getId() : null;
    }
}
