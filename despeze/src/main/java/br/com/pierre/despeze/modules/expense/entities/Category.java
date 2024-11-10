package br.com.pierre.despeze.modules.expense.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 50, message = "O nome da categoria deve ter entre 1 e 50 caracteres.")
    private String name;

    @Size(max = 255, message = "A descrição da categoria pode ter até 255 caracteres.")
    private String description;

}