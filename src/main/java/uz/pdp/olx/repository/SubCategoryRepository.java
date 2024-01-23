package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.enitiy.SubCategory;
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {

}
