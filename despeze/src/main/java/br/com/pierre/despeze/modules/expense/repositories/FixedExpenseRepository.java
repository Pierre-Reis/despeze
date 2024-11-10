package br.com.pierre.despeze.modules.expense.repositories;

import br.com.pierre.despeze.modules.expense.entities.FixedExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixedExpenseRepository extends JpaRepository<FixedExpense, UUID> {

}