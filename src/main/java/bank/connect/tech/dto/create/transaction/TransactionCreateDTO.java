package bank.connect.tech.dto.create.transaction;

import bank.connect.tech.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransactionCreateDTO {

    //@JsonProperty("type")
    //@NotEmpty
    //private String type;
    //@JsonProperty("status")
    //@NotEmpty
    //private String status;
    @JsonProperty("medium")
    @NotEmpty
    private String medium;
    @JsonProperty("amount")
    @NotNull
    private Double amount;
    @JsonProperty("description")
    private String description;

    @JsonProperty("senderAccount")
    @NotEmpty
    private Account senderAccount;
    @JsonProperty("ReceiverAccount")
    @NotEmpty
    private Account receiverAccount;


    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    //public String getType() {return this.type;}
    //public void setType(String type) {this.type = type;}

    //public String getStatus() {return this.status;}
    //public void setStatus(String status) {this.status = status;}

    public String getMedium() {return this.medium;}
    public void setMedium(String medium) {this.medium = medium;}

    public Double getAmount() {return this.amount;}
    public void setAmount(Double amount) {this.amount = amount;}

    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}
}
