package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.enitiy.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
