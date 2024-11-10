package br.com.pierre.despeze.modules.expense.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "installment_expenses")
public class InstallmentExpense extends DefaultExpense {

    protected InstallmentExpense(DefaultExpenseBuilder<?, ?> b) {
        super(b);
    }

    @NotNull(message = "O número de parcelas é obrigatório.")
    private Integer installmentCount;

    private Boolean useFullCreditLimit;

    @OneToMany(mappedBy = "installmentExpense", cascade = CascadeType.ALL)
    private List<Expense> expenses;

}