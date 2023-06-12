package bank.connect.tech.model.enumeration;

import bank.connect.tech.response.exception.MissingPropertyException;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
    P2P ("P2P"),
    DEPOSIT ("Deposit"),
    WITHDRAWAL ("Recurring");

    private final String status;

    private TransactionType (String status){
        this.status = status;

    }

    @Override
    @JsonValue
    public String toString() {
        return this.status;
    }

    public static TransactionType fromString(String statusString) {
        for (TransactionType status : TransactionType.values()) {
            if (status.toString().equalsIgnoreCase(statusString)) {
                return status;
            }
        }
        throw (new MissingPropertyException("No enum constant " + TransactionType.class.getCanonicalName()));
    }
}
