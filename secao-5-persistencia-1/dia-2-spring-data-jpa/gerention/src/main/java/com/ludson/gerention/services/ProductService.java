package com.ludson.gerention.services;

import com.ludson.gerention.models.entities.Product;
import com.ludson.gerention.models.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe contendo regras de negócios referente a Product.
 */
@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Inseri um novo produto no banco de dados.
   *
   * @param product corpo do produto a ser inserido no banco de dados.
   * @return o produto inserido.
   */
  public Product insertProduct(Product product) {
    return productRepository.save(product);
  }

  /**
   * Retorna o produto a partir do id.
   *
   * @param id do produto desejado.
   * @return optional do produto desejado.
   */
  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  /**
   * Retorna todos os produtos do banco de dados.
   *
   * @return list de todos os produtos no banco de dados.
   */
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  /**
   * Atualiza o produto no banco de dados a partir do id.
   *
   * @param id      do produto a ser atualizado.
   * @param product informações atualizadas do produto.
   * @return optional do produto alterado.
   */
  public Optional<Product> updateProduct(Long id, Product product) {
    Optional<Product> opProduct = productRepository.findById(id);

    if (opProduct.isPresent()) {
      Product productFromDb = opProduct.get();
      productFromDb.setName(product.getName());
      productFromDb.setPrice(product.getPrice());

      Product updatedProduct = productRepository.save(productFromDb);
      return Optional.of(updatedProduct);
    }

    return opProduct;
  }

  /**
   * Deleta um produto do banco de dados a partir do id.
   *
   * @param id do produto a ser deletado.
   * @return optional do produto deletado.
   */
  public Optional<Product> deleteProduct(Long id) {
    Optional<Product> opProduct = productRepository.findById(id);
    opProduct.ifPresent(product -> productRepository.deleteById(id));
    return opProduct;
  }
}