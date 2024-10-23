package br.com.pierre.despeze.modules.bank.repositories;

import br.com.pierre.despeze.modules.bank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

}