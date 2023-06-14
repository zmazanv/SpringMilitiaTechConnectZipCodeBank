package bank.connect.tech.dto.create.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class P2PTransactionCreateDTO {

    //@JsonProperty("type")
    //@NotEmpty
    //private String type;
    //@JsonProperty("status")
    //@NotEmpty
    //private String status;
    @JsonProperty("receiver_account_id")
    @NotNull
    private Long receiverAccountId;
    @JsonProperty("medium")
    @NotEmpty
    private String medium;
    @JsonProperty("amount")
    @NotNull
    private Double amount;
    @JsonProperty("description")
    private String description;


    //public String getType() {return this.type;}
    //public void setType(String type) {this.type = type;}

    //public String getStatus() {return this.status;}
    //public void setStatus(String status) {this.status = status;}

    public Long getReceiverAccountId() {return this.receiverAccountId;}
    public void setReceiverAccountId(Long receiverAccountId) {this.receiverAccountId = receiverAccountId;}

    public String getMedium() {return this.medium;}
    public void setMedium(String medium) {this.medium = medium;}

    public Double getAmount() {return this.amount;}
    public void setAmount(Double amount) {this.amount = amount;}

    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}
}
