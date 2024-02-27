package com.ludson.gerention.services;

import com.ludson.gerention.models.entities.Brand;
import com.ludson.gerention.models.repositories.BrandRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe contendo regras de negócios referente a Brand.
 */
@Service
public class BrandService {
  private BrandRepository brandRepository;

  @Autowired
  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  // create
  public Brand insertBrand(Brand brand) {
    return brandRepository.save(brand);
  }

  // read
  public Optional<Brand> getBrandById(Long id) {
    return brandRepository.findById(id);
  }

  // read all
  public List<Brand> getAllBrand() {
    return brandRepository.findAll();
  }

  /**
   * Atualiza a brand no Banco de dados.
   *
   * @param id id da brand a ser atualizada.
   * @param brand corpo a ser alterado.
   * @return retorna a brand alterada.
   */
  public Optional<Brand> updateBrand(Long id, Brand brand) {
    return brandRepository.findById(id)
        .map(brandFromDB -> {
          brandFromDB.setName(brand.getName());
          return brandRepository.save(brandFromDB);
        });
  }

  /**
   * Deleta a brand a partir do id.
   *
   * @param id id da brand a ser deletada.
   * @return retorna a brand que foi deletada.
   */
  public Optional<Brand> deleteBrand(Long id) {
    Optional<Brand> optionalBrand = brandRepository.findById(id);
    optionalBrand.ifPresent(brand -> brandRepository.deleteById(id));
    return optionalBrand;
  }
}
