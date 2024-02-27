package com.ludson.gerention.services;

import com.ludson.gerention.models.entities.Category;
import com.ludson.gerention.models.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para da camada de serviço contendo as regras de negócio da entidade Category.
 */
@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  // Create category
  public Category insertCategory(Category category) {
    return categoryRepository.save(category);
  }

  // Read category unit.
  public Optional<Category> getCategoryById(Long id) {
    return categoryRepository.findById(id);
  }

  // Read all category.
  public List<Category> getAllCategory() {
    return categoryRepository.findAll();
  }

  /**
   * Atualiza a categoria.
   *
   * @param id id da categoria desejada
   * @param category informações atualizadas da categoria
   * @return optional category
   */
  public Optional<Category> updateCategory(Long id, Category category) {
    return categoryRepository.findById(id)
        .map(categoryFromDB -> {
          categoryFromDB.setName(category.getName());
          return categoryRepository.save(categoryFromDB);
        });
  }

  /**
   * Deleta a categoria a partir do ID.
   *
   * @param id id da categoria a ser deletada.
   * @return retorna a categoria deletada como optional.
   */
  public Optional<Category> deleteCategory(Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    optionalCategory.ifPresent(category -> categoryRepository.deleteById(id));
    return optionalCategory;
  }
}
