package br.com.pierre.despeze.modules.expense.dto;

import br.com.pierre.despeze.modules.expense.entities.DefaultExpense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record InstallmentExpenseDTO(

        UUID categoryId,

        UUID bankAccountId,

        LocalDate expenseDate,

        BigDecimal amount,

        int installmentCount,

        DefaultExpense.MethodPayment methodPayment,  // Adicionei para garantir que você tenha o método de pagamento

        Boolean useFullCreditLimit,

        String description,

        String notes
) {}
