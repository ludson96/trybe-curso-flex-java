package com.ludson.gerention.services;

import com.ludson.gerention.models.entities.Category;
import com.ludson.gerention.models.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe contendo regras de negócios referente a Category.
 */
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  /**
   * Inseri uma nova categoria no banco de dados.
   *
   * @param category corpo da categoria a ser inserida no banco de dados.
   * @return a categoria inserida.
   */
  public Category insertCategory(Category category) {
    return categoryRepository.save(category);
  }

  /**
   * Retorna a categoria a partir do id.
   *
   * @param id da categoria desejado.
   * @return optional da categoria desejada.
   */
  public Optional<Category> getCategoryById(Long id) {
    return categoryRepository.findById(id);
  }

  /**
   * Retorna todas as categorias do banco de dados.
   *
   * @return list de todos as categorias no banco de dados.
   */
  public List<Category> getAllCategory() {
    return categoryRepository.findAll();
  }

  /**
   * Atualiza a categoria a partir do id.
   *
   * @param id       da categoria desejada.
   * @param category informações atualizadas da categoria.
   * @return optional da categoria alterado.
   */
  public Optional<Category> updateCategory(Long id, Category category) {
    return categoryRepository.findById(id)
        .map(categoryFromDB -> {
          categoryFromDB.setName(category.getName());
          return categoryRepository.save(categoryFromDB);
        });
  }

  /**
   * Deleta uma categoria do banco de dados a partir do id.
   *
   * @param id da categoria a ser deletada.
   * @return optional da categoria deletada.
   */
  public Optional<Category> deleteCategory(Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    optionalCategory.ifPresent(category -> categoryRepository.deleteById(id));
    return optionalCategory;
  }
}
