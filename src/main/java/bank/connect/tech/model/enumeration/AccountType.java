package bank.connect.tech.model.enumeration;

import bank.connect.tech.response.exception.MissingPropertyException;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {

    SAVINGS("Savings"),
    CHECKING("Checking"),
    CREDIT("Credit");


    private final String type;


    private AccountType(String type) {
        this.type = type;
    }


    @Override
    @JsonValue
    public String toString() {
        return this.type;
    }

    public static AccountType fromString(String typeString) {
        for (AccountType type : AccountType.values()) {
            if (type.toString().equalsIgnoreCase(typeString)) {
                return type;
            }
        }
        throw (new MissingPropertyException("No enum constant " + AccountType.class.getCanonicalName()));
    }
}