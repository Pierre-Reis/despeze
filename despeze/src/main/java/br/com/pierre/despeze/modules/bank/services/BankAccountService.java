package br.com.pierre.despeze.modules.bank.services;

import br.com.pierre.despeze.modules.bank.entities.BankAccount;
import br.com.pierre.despeze.modules.bank.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount create(BankAccount bankAccount){
        return this.bankAccountRepository.save(bankAccount);
    }
}