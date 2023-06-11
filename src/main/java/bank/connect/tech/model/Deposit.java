package bank.connect.tech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long Id;

    @Column(name = "type")
    @JsonProperty("type")
    private String type;
    @Column(name = "transaction_date")
    @JsonProperty("transaction_date" )
    private String transaction_date;
    @Column(name = "status")
    @JsonProperty("status")
    private String status;
    @Column(name = "payee_id")
    @JsonProperty ( "payee_id")
    private Long payee_id;

    @Column(name = "medium")
    @JsonProperty( "medium")
    private String medium;
    @Column(name = " amount")
    @JsonProperty("amount")
    private Double amount;
    @Column(name = "description")
    @JsonProperty ("description")
    private String description;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
