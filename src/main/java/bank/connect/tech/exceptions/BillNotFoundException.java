package bank.connect.tech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BillNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUUID = 1L;
    public BillNotFoundException(){}
    public BillNotFoundException(String message){
        super(message);
    }
    public BillNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
