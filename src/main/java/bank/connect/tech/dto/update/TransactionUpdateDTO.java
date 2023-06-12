package bank.connect.tech.dto.update;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionUpdateDTO {

    @JsonProperty("status")
    private String status;
    @JsonProperty("description")
    private String description;


    public String getStatus() {return this.status;}
    public void setStatus(String status) {this.status = status;}

    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}
}
