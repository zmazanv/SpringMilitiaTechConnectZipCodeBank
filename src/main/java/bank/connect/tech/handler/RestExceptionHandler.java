package bank.connect.tech.handler;

import bank.connect.tech.dto.ErrorDetail;
import bank.connect.tech.dto.ErrorResponse;
import bank.connect.tech.exceptions.BillNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(BillNotFoundException billNotFoundException){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(billNotFoundException.getMessage());
        errorDetail.setDeveloperMessage(billNotFoundException.getClass().getName());
        return (new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND));
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException httpMessageNotReadableException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus((short) status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDeveloperMessage(httpMessageNotReadableException.getClass().getName());
        return super.handleExceptionInternal(httpMessageNotReadableException, errorDetail, headers, status, request);
    }


}
