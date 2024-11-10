package br.com.pierre.despeze.modules.expense.repositories;

import br.com.pierre.despeze.modules.expense.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}