package bank.connect.tech.model;

import bank.connect.tech.model.enumeration.TransactionMedium;
import bank.connect.tech.model.enumeration.TransactionStatus;
import bank.connect.tech.model.enumeration.TransactionType;

import java.time.LocalDate;

public class Deposit {
    private Long id;
    private TransactionType type;
    private LocalDate transaction_date;
    private TransactionStatus status;
    private Long payee_id;
    private TransactionMedium medium;
    private Double amount;
    private String description;
}
