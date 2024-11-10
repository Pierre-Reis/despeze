package br.com.pierre.despeze.modules.expense.controllers;

import br.com.pierre.despeze.modules.expense.dto.InstallmentExpenseDTO;
import br.com.pierre.despeze.modules.expense.entities.InstallmentExpense;
import br.com.pierre.despeze.modules.expense.services.InstallmentExpenseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/installment-expenses")
public class InstallmentExpenseController {

    @Autowired
    private InstallmentExpenseService installmentExpenseService;

    @PostMapping("/create")
    public ResponseEntity<Object> createInstallmentExpense(@Valid @RequestBody InstallmentExpenseDTO installmentExpenseDTO, HttpServletRequest request) {
        try {
            var userId = UUID.fromString(request.getAttribute("user_id").toString());

            InstallmentExpense installmentExpense =
                    InstallmentExpense.builder()
                            .userId(userId)
                            .useFullCreditLimit(installmentExpenseDTO.useFullCreditLimit())
                            .categoryId(installmentExpenseDTO.categoryId())
                            .bankAccountId(installmentExpenseDTO.bankAccountId())
                            .expenseDate(installmentExpenseDTO.expenseDate())
                            .amount(installmentExpenseDTO.amount())
                            .installmentCount(installmentExpenseDTO.installmentCount())
                            .description(installmentExpenseDTO.description())
                            .methodPayment(installmentExpenseDTO.methodPayment())
                            .notes(installmentExpenseDTO.notes())
                            .build();

            var installment = installmentExpenseService.createInstallmentExpense(installmentExpense);

            return ResponseEntity.ok().body(installment);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar compra parcelada: " + ex.getMessage());
        }
    }
}