package bank.connect.tech.model;

import bank.connect.tech.model.enumeration.TransactionType;

public class Deposit {
    private Long id;
    private TransactionType type;
    private String transaction_date;
    private String status;
    private Long payee_id;
    private String medium;
    private Double amount;
    private String description;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getTransactionDate() {
        return transaction_date;
    }

    public void setTransactionDate(String transaction_date) {

        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getPayeeId() {
        return payee_id;
    }

    public void setPayeeId(Long payee_id) {

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
