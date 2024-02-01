package uz.pdp.olx.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.dto.categorydto.CategoryUpdateDto;
import uz.pdp.olx.dto.categorydto.ParentCategoryDto;
import uz.pdp.olx.enitiy.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

    @Query("select new uz.pdp.olx.dto.categorydto.ParentCategoryDto(c) from Category c where c.id = :id" )
    Optional<ParentCategoryDto> findParentCategoryById(@Param("id") Long id);

//    @Query("select uz.pdp.olx.dto.categorydto.CategoryUpdateDto from Category c where  c.parentCategory. = :parent_category_id")
//   Optional<CategoryUpdateDto> findByParentCategoryId(@Param("parent_category_id") Long parent_category_id);
    @Override
    void deleteById(@NonNull Long id);

//    @Override
//    List<Category> findAll();

    @Override
    boolean existsById(@NonNull Long id);
}
