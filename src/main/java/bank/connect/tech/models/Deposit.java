package bank.connect.tech.models;

import bank.connect.tech.model.Account;

public class Deposit {
    private Long id;

    private Long payeeId;
    private DepositMedium medium;
    private Double amount;
    private String description;

    public Deposit() {
    }

    public Deposit(Long id, , Long payeeId, DepositMedium medium, Double amount, String description) {
        this.id = id;
        this.payeeId = payeeId;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public DepositMedium getMedium() {
        return medium;
    }

    public void setMedium(DepositMedium medium) {
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

    public void setAccount(Account account) {

    }
}










