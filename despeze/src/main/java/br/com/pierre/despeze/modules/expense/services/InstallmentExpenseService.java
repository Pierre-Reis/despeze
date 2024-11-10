package br.com.pierre.despeze.modules.expense.services;

import br.com.pierre.despeze.modules.bank.entities.BankAccount;
import br.com.pierre.despeze.modules.bank.repositories.BankAccountRepository;
import br.com.pierre.despeze.modules.expense.entities.Expense;
import br.com.pierre.despeze.modules.expense.entities.InstallmentExpense;
import br.com.pierre.despeze.modules.expense.repositories.ExpenseRepository;
import br.com.pierre.despeze.modules.expense.repositories.InstallmentExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class InstallmentExpenseService {

    private final InstallmentExpenseRepository installmentExpenseRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ExpenseRepository expenseRepository;

    public InstallmentExpenseService(InstallmentExpenseRepository installmentExpenseRepository,
                                     BankAccountRepository bankAccountRepository,
                                     ExpenseRepository expenseRepository) {
        this.installmentExpenseRepository = installmentExpenseRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.expenseRepository = expenseRepository;
    }

    public InstallmentExpense createInstallmentExpense(InstallmentExpense installment) {
        validateInstallmentUser(installment);
        linkBankAccountToInstallment(installment);

        InstallmentExpense savedInstallment = installmentExpenseRepository.save(installment);

        BigDecimal installmentAmount = calculateInstallmentAmount(installment.getAmount(), installment.getInstallmentCount());
        BigDecimal remainder = calculateRemainder(installment.getAmount(), installmentAmount, installment.getInstallmentCount());

        createAndSaveExpensesForInstallments(savedInstallment, installmentAmount, remainder);

        return savedInstallment;
    }

    private void validateInstallmentUser(InstallmentExpense installment) {
        if (installment.getUserId() == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo.");
        }
    }

    private void linkBankAccountToInstallment(InstallmentExpense installment) {
        BankAccount bankAccount = bankAccountRepository.findById(installment.getBankAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Conta bancária não encontrada"));
        installment.setBankAccount(bankAccount);
    }

    private BigDecimal calculateInstallmentAmount(BigDecimal totalAmount, int installmentCount) {
        return totalAmount.divide(BigDecimal.valueOf(installmentCount), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateRemainder(BigDecimal totalAmount, BigDecimal installmentAmount, int installmentCount) {
        return totalAmount.subtract(installmentAmount.multiply(BigDecimal.valueOf(installmentCount)));
    }

    private void createAndSaveExpensesForInstallments(InstallmentExpense installment, BigDecimal installmentAmount, BigDecimal remainder) {
        for (int i = 1; i <= installment.getInstallmentCount(); i++) {
            BigDecimal adjustedAmount = (i == 1) ? installmentAmount.add(remainder) : installmentAmount;
            Expense singleInstallment = buildSingleInstallment(installment, i, adjustedAmount);
            expenseRepository.save(singleInstallment);
        }
    }

    private Expense buildSingleInstallment(InstallmentExpense installment, int installmentNumber, BigDecimal installmentAmount) {
        int dueDay = installment.getBankAccount().getDueDay();
        LocalDate installmentDate = calculateInstallmentDate(installment.getExpenseDate(), dueDay, installmentNumber);

        return Expense.builder()
                .description(installment.getDescription() + " - Parcela " + installmentNumber)
                .amount(installmentAmount)
                .methodPayment(installment.getMethodPayment())
                .userId(installment.getUserId())
                .bankAccountId(installment.getBankAccountId())
                .categoryId(installment.getCategoryId())
                .expenseDate(installmentDate)
                .installmentExpenseId(installment.getId())
                .installmentNumber(installmentNumber)
                .isPaid(false)
                .build();
    }

    private LocalDate calculateInstallmentDate(LocalDate baseDate, int dueDay, int installmentNumber) {
        return baseDate.plusMonths(installmentNumber - 1)
                .withDayOfMonth(Math.min(dueDay, baseDate.plusMonths(installmentNumber - 1).lengthOfMonth()));
    }
}