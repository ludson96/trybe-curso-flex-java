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

  private final BrandRepository brandRepository;

  @Autowired
  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  /**
   * Inseri uma nova brand no banco de dados.
   *
   * @param brand corpo da brand a ser inserida no banco de dados.
   * @return a brand inserida.
   */
  public Brand insertBrand(Brand brand) {
    return brandRepository.save(brand);
  }

  /**
   * Retorna a brand a partir do id.
   *
   * @param id da brand desejada.
   * @return optional da brand desejada.
   */
  public Optional<Brand> getBrandById(Long id) {
    return brandRepository.findById(id);
  }

  /**
   * Retorna todas as brands do banco de dados.
   *
   * @return list de todas as brands no banco de dados.
   */
  public List<Brand> getAllBrand() {
    return brandRepository.findAll();
  }

  /**
   * Atualiza a brand no Banco de dados a partir do id.
   *
   * @param id    da brand a ser atualizada.
   * @param brand informações atualizadas da brand.
   * @return optional da brand alterada.
   */
  public Optional<Brand> updateBrand(Long id, Brand brand) {
    return brandRepository.findById(id)
        .map(brandFromDB -> {
          brandFromDB.setName(brand.getName());
          return brandRepository.save(brandFromDB);
        });
  }

  /**
   * Deleta uma brand do banco de dados a partir do id.
   *
   * @param id da brand a ser deletada.
   * @return optional da brand deletada.
   */
  public Optional<Brand> deleteBrand(Long id) {
    Optional<Brand> optionalBrand = brandRepository.findById(id);
    optionalBrand.ifPresent(brand -> brandRepository.deleteById(id));
    return optionalBrand;
  }
}
