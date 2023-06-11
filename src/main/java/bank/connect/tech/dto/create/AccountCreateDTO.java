package bank.connect.tech.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

public class AccountCreateDTO {

    @NotEmpty
    @JsonProperty("type")
    private String type;
    @JsonProperty("nickname")
    private String nickname;


    public String getType() {return this.type;}
    public void setType(String type) {this.type = type;}

    public String getNickname() {return this.nickname; }
    public void setNickname(String nickname) {this.nickname = nickname;}
}
