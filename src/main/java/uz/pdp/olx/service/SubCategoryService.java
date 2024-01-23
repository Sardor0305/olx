package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.olx.enitiy.SubCategory;
import uz.pdp.olx.repository.CategoryRepository;
import uz.pdp.olx.repository.SubCategoryRepository;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;


    public SubCategory findById(Long id){
     return  subCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }







}
