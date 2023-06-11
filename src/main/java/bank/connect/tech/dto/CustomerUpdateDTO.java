package bank.connect.tech.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import bank.connect.tech.model.Address;
import java.util.Set;

public class CustomerUpdateDTO {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("addresses")
    private Set<Address> addresses;


    public String getFirstName() {return this.firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public Set<Address> getAddresses() {return this.addresses;}
    public void setAddresses(Set<Address> addresses) {this.addresses = addresses;}
}
