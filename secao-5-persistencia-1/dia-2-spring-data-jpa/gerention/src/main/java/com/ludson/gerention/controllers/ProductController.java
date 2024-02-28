package com.ludson.gerention.controllers;

import com.ludson.gerention.dto.CreateProductDTO;
import com.ludson.gerention.dto.ResponseProductDTO;
import com.ludson.gerention.models.entities.Product;
import com.ludson.gerention.services.ProductService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller da rota "/products".
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private final ProductService productService;

  @Autowired

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Controller que inseri um novo produto no banco de dados.
   *
   * @param productDto dto de input do novo produto.
   * @return responseProductDto de produto com mensagem e o produto novo produto.
   */
  @PostMapping
  public ResponseEntity<ResponseProductDTO<Product>> insertProduct(
      @RequestBody CreateProductDTO productDto) {
    Product newProduct = productService.insertProduct(productDto.toProduct());
    ResponseProductDTO<Product> responseProductDto = new ResponseProductDTO<>(
        "Produto criado com sucesso", newProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseProductDto);
  }

  /**
   * Controller que retorna um produto a partir do id.
   *
   * @param id do produto desejado.
   * @return responseProductDto de produto com mensagem e o produto.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseProductDTO<Product>> getProductById(
      @PathVariable Long id) {
    return productService.getProductById(id)
        .map(product -> ResponseEntity.ok(
            new ResponseProductDTO<>("Produto encontrado com sucesso!", product)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseProductDTO<>(String.format("Não foi encontrado produto de id %d", id),
                null)));

  }

  /**
   * Controller que retorna todos os produtos.
   *
   * @return responseProductDto um list com todos os produtos.
   */
  @GetMapping
  public ResponseEntity<ResponseProductDTO<List<Product>>> getAllProducts() {
    List<Product> allProducts = productService.getAllProducts();

    return ResponseEntity.ok()
        .body(new ResponseProductDTO<>("Todos os produtos encontrados!", allProducts));
  }

  /**
   * Controller que atualiza um produto a partir do id.
   *
   * @param id               do produto a ser atualizado
   * @param createProductDTO informações atualizadas do produto.
   * @return uma mensagem com o produto atualizado.
   */
  @PutMapping("{id}")
  public ResponseEntity<ResponseProductDTO<Product>> updateProduct(
      @PathVariable Long id,
      @RequestBody CreateProductDTO createProductDTO
  ) {
    return productService.updateProduct(id, createProductDTO.toProduct())
        .map(product -> ResponseEntity.ok(
            new ResponseProductDTO<>("Produto atualizado com sucesso!", product)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseProductDTO<>(
                String.format("Não foi encontrado produto com o id %d", id), null)));
  }

  /**
   * Controller que deleta um produto do banco de dados a partir do id.
   *
   * @param id do produto a ser deletado.
   * @return uma mensagem com o produto deletado.
   */
  @DeleteMapping("{id}")
  public ResponseEntity<ResponseProductDTO<Product>> deleteProduct(
      @PathVariable Long id
  ) {
    return productService.deleteProduct(id)
        .map(product -> ResponseEntity.ok(
            new ResponseProductDTO<>("Produto deletado com sucesso", product)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseProductDTO<>(
                String.format("Não foi encontrado o produto com o id %d", id), null)));
  }
}
