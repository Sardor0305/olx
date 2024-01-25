package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.dto.ProductSaveDto;
import uz.pdp.olx.dto.ProductUpdateDto;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.exception.CategoryNotFoundException;
import uz.pdp.olx.exception.ProductNotFoundException;
import uz.pdp.olx.repository.CategoryRepository;
import uz.pdp.olx.repository.ProductRepository;
import uz.pdp.olx.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public ProductDto save(ProductSaveDto productSaveDto){
        log.info(productSaveDto.getCategoryId() + " ------ ");
        log.info(productSaveDto.getUserId() + " ------ ");
      Product product = new Product();
        product.setTitle(productSaveDto.getTitle());
        product.setDescription(productSaveDto.getDescription());
        product.setItemCondition(productSaveDto.getItemCondition());
        product.setPrice(productSaveDto.getPrice());
        product.setItemCondition(productSaveDto.getItemCondition());
        product.setUser(userRepository.findById(productSaveDto.getUserId()).orElseThrow(()-> new RuntimeException("user not f")));
        product.setCategory(categoryRepository.findById(productSaveDto.getCategoryId()).orElseThrow(
                CategoryNotFoundException::new
        ));
        return new ProductDto(productRepository.save(product));
    }



public Optional<ProductDto> findByProductId(Long id){
        return Optional.ofNullable(productRepository.finBYdId(id).orElseThrow(ProductNotFoundException::new));

}


public Optional<List<ProductDto>>findAllByProductTitle(String title){
        return Optional.ofNullable(productRepository.findAllByTitle(title).orElseThrow(
                ProductNotFoundException::new
        ));
}
public Boolean deleteByProductId(Long id){
        if(productRepository.existsById(id)){
             productRepository.deleteById(id);
             return true;
        }
        else {
            throw new ProductNotFoundException();
        }
}
public ProductDto updateProduct(ProductUpdateDto productUpdateDto){
    Product product = productRepository.findById(productUpdateDto.getId())
            .orElseThrow(ProductNotFoundException::new);
    product.setTitle(productUpdateDto.getTitle());
    product.setDescription(productUpdateDto.getDescription());
    product.setIsActive(productUpdateDto.getIsActive());
    product.setPrice(productUpdateDto.getPrice());
    product.setItemCondition(productUpdateDto.getItemCondition());
    product.setCategory(categoryRepository.findById(productUpdateDto.getCategoryId())
            .orElseThrow(CategoryNotFoundException::new));
    return new ProductDto(product);
}

}
