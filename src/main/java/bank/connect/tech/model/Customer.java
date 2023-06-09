package bank.connect.tech.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CustomerPOJO {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    @OneToOne
    private Set<Address> addresses;


    public CustomerPOJO() {
    }

    public CustomerPOJO(Long id, String first_name, String last_name, Set<String> addresses) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Set<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<String> addresses) {
        this.addresses = addresses;
    }
}
