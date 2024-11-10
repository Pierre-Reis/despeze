package br.com.pierre.despeze.modules.expense.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "expenses")
public class Expense extends DefaultExpense {

    protected Expense(DefaultExpenseBuilder<?, ?> b) {
        super(b);
    }

    private Integer installmentNumber;

    @ManyToOne
    @JoinColumn(name = "installment_expense_id", insertable = false, updatable = false)
    private InstallmentExpense installmentExpense;

    @Column(name = "installment_expense_id")
    private UUID installmentExpenseId;

    private Boolean isPaid = false;
}