package bank.connect.tech.model;

import bank.connect.tech.model.enumeration.TransactionType;

public class Withdrawal {
    private Long id;
    private TransactionType type;
    private String transaction_date;
    private String status;
    private Long payer_id;
    private String medium;
    private Double amount;
    private String description;

    // Getters

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public String getTransactionDate() {
        return transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public Long getPayerId() {
        return payer_id;
    }

    public String getMedium() {
        return medium;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setTransactionDate(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayerId(Long payer_id) {
        this.payer_id = payer_id;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

