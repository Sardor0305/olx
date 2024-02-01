package uz.pdp.olx.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.dto.productdto.ProductDto;
import uz.pdp.olx.enitiy.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("select new uz.pdp.olx.dto.productdto.ProductDto(p) from Product p where p.title = :title order by p.id desc ")
   Optional<List<ProductDto>> findAllByTitle(String title);
    @Query("select new uz.pdp.olx.dto.productdto.ProductDto(p) from Product p where p.id = :id order by p.id desc ")
    Optional<ProductDto> finBYdId(@NonNull Long id);

    @Query("SELECT p FROM Product p WHERE (SELECT COUNT(r) FROM ProductReview r WHERE r.product.id = p.id) >= 5")
    List<Product> findProductsWithMoreThanFiveReviews();

    @Override
    void deleteById(@NonNull Long id);

    @Modifying
    @Query("DELETE FROM Product u WHERE u.id = :productId")
    void deleteWithCascade(Long productId);

    @Override
    boolean existsById(@NonNull Long id);


}
