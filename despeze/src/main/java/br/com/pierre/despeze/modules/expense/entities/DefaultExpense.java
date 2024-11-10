package br.com.pierre.despeze.modules.expense.entities;

import br.com.pierre.despeze.modules.bank.entities.BankAccount;
import br.com.pierre.despeze.modules.user.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@SuperBuilder
@MappedSuperclass
public abstract class DefaultExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "O valor da despesa precisa ser preenchido")
    @DecimalMin(value = "0.0", message = "O valor da despesa precisa ser maior que 0")
    private BigDecimal amount;

    private String description;

    private String notes;

    @Enumerated(EnumType.STRING)
    private MethodPayment methodPayment;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", insertable = false, updatable = false)
    private BankAccount bankAccount;

    @Column(name = "bank_account_id", nullable = false)
    private UUID bankAccountId;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    public DefaultExpense() {}

    public enum MethodPayment {
        DEBITO, CREDITO, BOLETO, PIX
    }
}
