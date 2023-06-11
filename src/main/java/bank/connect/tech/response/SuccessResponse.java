package bank.connect.tech.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SuccessResponse<T> {
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public SuccessResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
    public SuccessResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {return this.code;}
    public void setCode(int code) {this.code = code;}

    public String getMessage() {return this.message;}
    public void setMessage(String message) {this.message = message;}

    public T getData() {return this.data;}
    public void setData(T data) {this.data = data;}
}
