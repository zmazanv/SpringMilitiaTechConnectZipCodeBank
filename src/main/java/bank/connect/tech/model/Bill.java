package bank.connect.tech.model;

import bank.connect.tech.model.enumeration.BillStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    @JsonProperty("status")
    private BillStatus billStatus;
    @Column(name = "payee")
    @JsonProperty("payee")
    private String payee;
    @Column(name = "nickname")
    @JsonProperty("nickname")
    private String nickname;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "payment_date")
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    @Column(name = "recurring_date")
    @JsonProperty("recurring_date")
    private Byte recurringDate; // FIXME: Put in failsafe to restrict value to be between 1 and 31
    @Column(name = "upcoming_payment_date")
    private LocalDate upcomingPaymentDate;
    @Column(name = "payment_amount")
    @JsonProperty("payment_amount")
    private Double paymentAmount;
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;


    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public BillStatus getBillStatus() {return this.billStatus;}
    public void setBillStatus(BillStatus billStatus) {this.billStatus = billStatus;}

    public String getPayee() {return this.payee;}
    public void setPayee(String payee) {this.payee = payee;}

    public String getNickname() {return this.nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}

    public LocalDate getCreationDate() {return this.creationDate;}
    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}

    public LocalDate getPaymentDate() {return this.paymentDate;}
    public void setPaymentDate(LocalDate paymentDate) {this.paymentDate = paymentDate;}

    public Byte getRecurringDate() {return this.recurringDate;}
    public void setRecurringDate(Byte recurringDate) {this.recurringDate = recurringDate;}

    public LocalDate getUpcomingPaymentDate() {return this.upcomingPaymentDate;}
    public void setUpcomingPaymentDate(LocalDate upcomingPaymentDate) {this.upcomingPaymentDate = upcomingPaymentDate;}

    public Double getPaymentAmount() {return this.paymentAmount;}
    public void setPaymentAmount(Double paymentAmount) {this.paymentAmount = paymentAmount;}

    @JsonIgnore
    public Account getAccount() {return this.account;}
    public void setAccount(Account account) {this.account = account;}

    @JsonProperty("account_id")
    public Long getAccountId() {
        return this.account != null ? this.account.getId() : null;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> main
