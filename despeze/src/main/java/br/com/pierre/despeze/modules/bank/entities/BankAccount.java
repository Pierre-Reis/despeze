package br.com.pierre.despeze.modules.bank.entities;

import br.com.pierre.despeze.modules.user.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 100, message = "O nome precisa estar entre 1 e 100 caracteres")
    private String name;

    @DecimalMin(value = "0.0", message = "O limite de crédito precisa ser um valor positivo.")
    private BigDecimal creditLimit;

    @Min( value = 1, message = "O dia de vencimento deve ser entre 1 e 31.")
    @Max( value = 31, message = "O dia de vencimento deve ser entre 1 e 31.")
    @Column(nullable = false)
    private Integer dueDay;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

}