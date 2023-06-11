package bank.connect.tech.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountUpdateDTO {

    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("rewards")
    private Integer rewards;
    @JsonProperty("balance")
    private Double balance;


    public String getNickname() {return this.nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}

    public Integer getRewards() {return this.rewards;}
    public void setRewards(Integer rewards) {this.rewards = rewards;}

    public Double getBalance() {return this.balance;}
    public void setBalance(Double balance) {this.balance = balance;}
}
