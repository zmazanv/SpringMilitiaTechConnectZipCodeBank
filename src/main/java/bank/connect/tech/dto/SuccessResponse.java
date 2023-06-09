package bank.connect.tech.dto;

import bank.connect.tech.model.Bill;

import java.util.List;

public class SuccessResponse {
    private int code;
    private List<Bill>data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Bill> getData() {
        return data;
    }

    public void setData(List<Bill> data) {
        this.data = data;
    }
}
