package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.olx.enitiy.Category;
import uz.pdp.olx.repository.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

  public Optional<Category> findById(Long id){
      return categoryRepository.findById(id);

  }



}
