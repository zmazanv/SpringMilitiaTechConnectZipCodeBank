package bank.connect.tech.model;

public enum AccountType {

    SAVINGS("Savings"),
    CHECKING("Checking"),
    CREDIT("Credit");


    private final String type;


    AccountType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return this.type;
    }

    public static AccountType fromString(String typeString) {
        for (AccountType type : AccountType.values()) {
            if (type.toString().equalsIgnoreCase(typeString)) {
                return type;
            }
        }
        throw (new IllegalArgumentException("No enum constant " + AccountType.class.getCanonicalName() + '.'));
    }
}
