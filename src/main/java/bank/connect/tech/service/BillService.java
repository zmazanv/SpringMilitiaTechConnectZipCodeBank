package bank.connect.tech.Service;


import bank.connect.tech.exceptions.ResourceNotFoundException;
import bank.connect.tech.model.Bill;
import bank.connect.tech.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
protected void verifyBill(Long id) throws ResourceNotFoundException {
    if(!(billRepository.existsById(id))){
        throw new ResourceNotFoundException("error fetching bills");
    }
}
    public Iterable<Bill> getAllBills(){
        return billRepository.findAll();
    }

    public Bill getBillById(Long id){
        verifyBill(id);
        return billRepository.findById(id).orElse(null);
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
