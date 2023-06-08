package bank.connect.tech.model;

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
    private String street_number;

    @Column(name = "street_name")
    @NotEmpty
    private String street_name;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "state")
    @NotEmpty
    private String state;

    @Column(name = "zip")
    @NotEmpty
    private String zip;


    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getStreet_number() {return this.street_number;}
    public void setStreet_number(String street_number) {this.street_number = street_number;}

    public String getStreet_name() {return this.street_name;}
    public void setStreet_name(String street_name) {this.street_name = street_name;}

    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}

    public String getState() {return this.state;}
    public void setState(String state) {this.state = state;}

    public String getZip() {return this.zip;}
    public void setZip(String zip) {this.zip = zip;}
}
