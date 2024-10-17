package br.com.pierre.despeze.modules.user.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String firstname;

    @NotBlank
    @Size(min = 1, max = 50)
    private String lastname;

    @Past
    private LocalDate birthdate;

    @Email(message = "O campo (e-mail) deve conter um e-mail válido.")
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

}