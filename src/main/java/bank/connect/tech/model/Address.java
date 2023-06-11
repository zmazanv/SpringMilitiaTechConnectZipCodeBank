package bank.connect.tech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street_number")
    @NotEmpty
    @JsonProperty("street_number")
    private String streetNumber;
    @Column(name = "street_name")
    @NotEmpty
    @JsonProperty("street_name")
    private String streetName;
    @Column(name = "city")
    @NotEmpty
    @JsonProperty("city")
    private String city;
    @Column(name = "state")
    @NotEmpty
    @JsonProperty("state")
    private String state;
    @Column(name = "zip")
    @NotEmpty
    @JsonProperty("zip")
    private String zip;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getStreetNumber() {return this.streetNumber;}
    public void setStreetNumber(String streetNumber) {this.streetNumber = streetNumber;}

    public String getStreetName() {return this.streetName;}
    public void setStreetName(String streetName) {this.streetName = streetName;}

    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}

    public String getState() {return this.state;}
    public void setState(String state) {this.state = state;}

    public String getZip() {return this.zip;}
    public void setZip(String zip) {this.zip = zip;}

    public Customer getCustomer() {return this.customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}
}
