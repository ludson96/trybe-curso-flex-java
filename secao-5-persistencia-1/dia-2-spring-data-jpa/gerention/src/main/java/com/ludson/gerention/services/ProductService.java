package com.ludson.gerention.services;

import com.ludson.gerention.models.entities.Product;
import com.ludson.gerention.models.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product insertProduct(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> updateProduct(Long id, Product product) {
    Optional<Product> opProduct = productRepository.findById(id);

    if (opProduct.isPresent()) {
      Product productFromDB = opProduct.get();
      productFromDB.setName(product.getName());
      productFromDB.setPrice(product.getPrice());

      Product updatedProduct = productRepository.save(productFromDB);
      return Optional.of(updatedProduct);
    }

    return opProduct;
  }

  public Optional<Product> deleteProduct(Long id) {
    Optional<Product> opProduct = productRepository.findById(id);
    opProduct.ifPresent(product -> productRepository.deleteById(id));
    return opProduct;
  }

  public Optional<Product> getBookById(Long id) {
    return productRepository.findById(id);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}
