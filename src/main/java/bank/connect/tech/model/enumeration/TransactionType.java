package bank.connect.tech.model.enumeration;

import bank.connect.tech.response.exception.MissingPropertyException;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {

    P2P("P2P"),
    DEPOSIT("Deposit"),
    WITHDRAWAL("Recurring");


    private final String type;


    private TransactionType (String type){
        this.type = type;

    }


    @Override
    @JsonValue
    public String toString() {
        return this.type;
    }

    public static TransactionType fromString(String typeString) {
        for (TransactionType type : TransactionType.values()) {
            if (type.toString().equalsIgnoreCase(typeString)) {
                return type;
            }
        }
        throw (new MissingPropertyException("No enum constant " + TransactionType.class.getCanonicalName()));
    }
}
