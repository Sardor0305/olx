package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.enitiy.ProductReview;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    void deleteByProductId(Long id);
}