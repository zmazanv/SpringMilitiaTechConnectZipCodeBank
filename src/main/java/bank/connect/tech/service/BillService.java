package bank.connect.tech.service;

import bank.connect.tech.model.Bill;
import bank.connect.tech.repository.BillRepository;

import java.util.List;

public class BillService {
    private BillRepository billRepository;

    public List<Bill> getAllBills(){
        return (List<Bill>) billRepository.findAll();
    }

    public Bill getBillById(Long id){
        return billRepository.findById(id).orElse(null);
    }

    public Bill createBill(Bill bill){
        return billRepository.save(bill);
    }

    public void updateBill(Bill bill, Long id){
        bill.setId(id);
        billRepository.save(bill);
    }

    public void deleteBill(Long id){
        billRepository.deleteById(id);
    }

    public List<Bill> getCustomerBillById(Long account_id){
        return (List<Bill>) billRepository.findById(account_id).orElse(null);
    }

}
