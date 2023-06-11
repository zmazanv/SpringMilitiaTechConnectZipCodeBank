package bank.connect.tech.dto;

import javax.validation.constraints.NotEmpty;

public class AccountDTO {

    private String nickname;
    @NotEmpty
    private String type;


    public String getNickname() {return this.nickname; }
    public void setNickname(String nickname) {this.nickname = nickname;}

    public String getType() {return this.type;}
    public void setType(String type) {this.type = type;}
}
