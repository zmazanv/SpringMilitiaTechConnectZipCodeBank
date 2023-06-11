package bank.connect.tech.model.enumeration;

import bank.connect.tech.response.exception.MissingPropertyException;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BillStatus {

    PENDING("Pending"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    RECURRING("Recurring");

    private final String status;

    private BillStatus(String status) {
        this.status = status;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.status;
    }

    public static BillStatus fromString(String statusString) {
        for (BillStatus status : BillStatus.values()) {
            if (status.toString().equalsIgnoreCase(statusString)) {
                return status;
            }
        }
        throw (new MissingPropertyException("No enum constant " + BillStatus.class.getCanonicalName()));
    }
}
