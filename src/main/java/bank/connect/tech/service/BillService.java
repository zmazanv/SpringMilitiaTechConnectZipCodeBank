package bank.connect.tech.service;

import bank.connect.tech.exceptions.BillNotFoundException;
import bank.connect.tech.model.Bill;
import bank.connect.tech.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
protected void verifyBill(Long id) throws BillNotFoundException{
    if(!(billRepository.existsById(id))){
        throw new BillNotFoundException("error fetching bills");
    }
}
    public List<Bill> getAllBills(){
        return (List<Bill>) billRepository.findAll();
    }

    public Iterable<Bill> getBillById(Long id){
        verifyBill(id);
        return (Iterable<Bill>) billRepository.findById(id).orElse(null);
    }

    public Bill createBill(Bill bill){
        return billRepository.save(bill);
    }

    public void updateBill(Bill bill, Long id){
        verifyBill(id);
        bill.setId(id);
        billRepository.save(bill);
    }

    public void deleteBill(Long id){
        verifyBill(id);
        billRepository.deleteById(id);
    }

    public Iterable<Bill> getCustomerBillById(Long account_id){
        return billRepository.findAllBillsByAccountId(account_id);
    }

}
