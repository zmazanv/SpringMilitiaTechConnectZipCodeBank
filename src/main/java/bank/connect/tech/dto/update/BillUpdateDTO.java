package bank.connect.tech.dto.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class BillUpdateDTO {

    @JsonProperty("status")
    private String status;
    @JsonProperty("payee")
    private String payee;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    @JsonProperty("recurring_date")
    private Byte recurringDate;
    @JsonProperty("payment_amount")
    private Double paymentAmount;


    public String getStatus() {return this.status;}
    public void setStatus(String status) {this.status = status;}

    public String getPayee() {return this.payee;}
    public void setPayee(String payee) {this.payee = payee;}

    public String getNickname() {return this.nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}

    public LocalDate getPaymentDate() {return this.paymentDate;}
    public void setPaymentDate(LocalDate paymentDate) {this.paymentDate = paymentDate;}

    public Byte getRecurringDate() {return this.recurringDate;}
    public void setRecurringDate(Byte recurringDate) {this.recurringDate = recurringDate;}

    public Double getPaymentAmount() {return this.paymentAmount;}
    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
