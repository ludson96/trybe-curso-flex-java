package com.ludson.gerention.dto;

import com.ludson.gerention.models.entities.Product;

public record CreateProductDTO(String name, Double price) {

  public Product toProduct() {
    return new Product(name, price);
  }
}