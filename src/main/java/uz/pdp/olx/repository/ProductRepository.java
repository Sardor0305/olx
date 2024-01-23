package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.enitiy.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//    @Query("select new uz.pdp.olx.dto.ProductDto(p) from Product p where p.title = :title")
    Optional<ProductDto> findByTitle(String title);

   Optional<List<ProductDto>> findAllByTitle(String title);



}
