package bank.connect.tech.model.enumeration;

import bank.connect.tech.response.exception.MissingPropertyException;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionMedium {

    BALANCE("Balance"),
    REWARDS("Rewards");


    private final String medium;


    private TransactionMedium(String medium) {
        this.medium = medium;
    }


    @Override
    @JsonValue
    public String toString() {
        return this.medium;
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
