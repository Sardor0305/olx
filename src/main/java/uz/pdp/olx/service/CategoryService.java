package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.categorydto.*;
import uz.pdp.olx.enitiy.Category;
import uz.pdp.olx.exception.CategoryNotFoundException;
import uz.pdp.olx.exception.ParentCategoryNotFoundException;
import uz.pdp.olx.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

  public Optional<Category> findById(Long id){
      return categoryRepository.findById(id);

  }

public ParentCategoryDto saveParentCategory(ParentSaveCategoryDto parentCategory){
    final  Category category = new Category();
    category.setName(parentCategory.getParentCategoryName());
    category.setParentImagePath(parentCategory.getParentImagePath());
    categoryRepository.save(category);
    return new ParentCategoryDto(category.getId(),category.getName(),category.getParentImagePath());
}
public ParentCategoryDto findParentCategoryById(Long id){

  return categoryRepository.findParentCategoryById(id)
            .orElseThrow(CategoryNotFoundException::new);
}

public ParentCategoryDto updateParentCategory(ParentUpdateCategoryDto parentUpdateCategoryDto){
    Category category = categoryRepository.findById(parentUpdateCategoryDto.getId())
            .orElseThrow(CategoryNotFoundException::new);
    category.setName(parentUpdateCategoryDto.getParentCategoryName());
    category.setParentImagePath(parentUpdateCategoryDto.getParentImagePath());
    categoryRepository.save(category);
    return new ParentCategoryDto(category.getId()
            ,category.getName()
            ,category.getParentImagePath());
}
public Boolean delete(Long id) {
    if (categoryRepository.existsById(id)) {
        categoryRepository.deleteById(id);
        return true;
    } else {
        throw new CategoryNotFoundException();
    }

}

public CategoryDto saveCategory(CategorySaveDto categorySaveDto){
      final  Category category = new Category();
    Category categoryAll = categoryRepository.findById(categorySaveDto.getParentCategoryId())
            .orElseThrow(CategoryNotFoundException::new);

        category.setName(categorySaveDto.getName());
        category.setParentCategory(categoryAll);
        categoryRepository.save(category);
        return new CategoryDto(category.getId(),category.getName(),findParentCategoryById(categorySaveDto.getParentCategoryId()));

}

public CategoryDto updateCategoryById(CategoryUpdateDto categoryUpdateDto){
   final Category category = categoryRepository.findById(categoryUpdateDto.getId())
            .orElseThrow(CategoryNotFoundException::new);

   final Category categoryAll = categoryRepository.findById(categoryUpdateDto.getParentCategoryId())
            .orElseThrow(ParentCategoryNotFoundException::new);



    if (categoryAll.getParentCategory()!=null){
        throw new CategoryNotFoundException();
    }
    else {
        category.setName(categoryUpdateDto.getName());
        category.setParentCategory(categoryAll);

        return new CategoryDto(category.getId(),category.getName(),findParentCategoryById(categoryUpdateDto.getParentCategoryId()));


    }

}
public Category findAllCategoryById(Long id){
      return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
}
//public CategoryUpdateDto findByParentCategoryId(Long parentCategoryId){
//       return categoryRepository.findByParentCategoryId(parentCategoryId).orElseThrow(CategoryNotFoundException::new);
//
//}
    public List<Category> all(){
      return categoryRepository.findAll();
    }











}
