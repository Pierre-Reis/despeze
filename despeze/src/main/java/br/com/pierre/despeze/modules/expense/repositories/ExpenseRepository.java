package br.com.pierre.despeze.modules.expense.repositories;

import br.com.pierre.despeze.modules.expense.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

}