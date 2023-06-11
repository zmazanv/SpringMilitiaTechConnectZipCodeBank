package bank.connect.tech.service;

import bank.connect.tech.model.Deposit;
import bank.connect.tech.repository.AccountRepository;
import bank.connect.tech.repository.DepositRepository;
import bank.connect.tech.response.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountRepository accountRepository;

    protected void verifyAccount (Long accountId, String exceptionMessage) throws ResourceNotFoundException{
        if (! (this.accountRepository.existsById(accountId))){
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }

    public Deposit getDepositById (Long id, String exceptionMessage){
        this.verifyAccount(id, exceptionMessage);
        return this.depositRepository.findById(id).get();
    }


}
