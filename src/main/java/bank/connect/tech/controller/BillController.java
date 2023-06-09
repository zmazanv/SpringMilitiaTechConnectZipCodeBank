package bank.connect.tech.controller;

import bank.connect.tech.model.Bill;
import bank.connect.tech.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class BillController {
    private BillService billservice;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBills(){
        return new ResponseEntity<>(billservice.getAllBills(), HttpStatus.OK);
    }
    @GetMapping("/bills/{billId}")
    public List<Bill> getBillWithId(@PathVariable Long id){
        return (List<Bill>) billservice.getBillById(id);
    }
    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?>getCustomerBill(Long account_id) {
        return new ResponseEntity<>(billservice.getCustomerBillById(account_id), HttpStatus.OK);
    }
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
        billservice.deleteBill(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/bills/{billId}")
    public ResponseEntity<Void> updateBill(@PathVariable Long id, @RequestBody Bill bill){
        billservice.updateBill(bill,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<Void>createBill(@RequestBody Bill bill){
        billservice.createBill(bill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
