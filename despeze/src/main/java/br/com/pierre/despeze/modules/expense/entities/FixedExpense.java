package br.com.pierre.despeze.modules.expense.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "fixed_expenses")
public class FixedExpense extends DefaultExpense {

    @Min( value = 1, message = "O dia de vencimento deve ser entre 1 e 31.")
    @Max( value = 31, message = "O dia de vencimento deve ser entre 1 e 31.")
    @Column(nullable = false)
    private Integer dueDay;

}