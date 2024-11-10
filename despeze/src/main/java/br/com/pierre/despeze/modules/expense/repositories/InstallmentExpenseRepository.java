package br.com.pierre.despeze.modules.expense.repositories;

import br.com.pierre.despeze.modules.expense.entities.InstallmentExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstallmentExpenseRepository extends JpaRepository<InstallmentExpense, UUID> {

}