package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.enitiy.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{


}
