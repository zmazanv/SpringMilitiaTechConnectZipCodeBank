package bank.connect.tech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    @Column(name = "first_name")
    @JsonProperty("first_name")
    @NotEmpty
    private String firstName;
    @Column(name = "last_name")
    @JsonProperty("last_name")
    @NotEmpty
    private String lastName;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonProperty("addresses")
    private Set<Address> addresses;


    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getFirstName() {return this.firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public Set<Address> getAddresses() {return this.addresses;}
    public void setAddresses(Set<Address> addresses) {this.addresses = addresses;}
}
