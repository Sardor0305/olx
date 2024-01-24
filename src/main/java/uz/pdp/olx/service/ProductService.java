package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.dto.ProductSaveDto;
import uz.pdp.olx.enitiy.Product;
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
//        product.setItemCondition(productSaveDto.getItemCondition());
//        product.setPrice(productSaveDto.getPrice());
//        product.setRate(productSaveDto.getRate());
//        product.setReviews(productSaveDto.getReviews());
        product.setUser(userRepository.findById(productSaveDto.getUserId()).orElseThrow(()-> new RuntimeException("user not f")));
        product.setCategory(categoryRepository.findById(productSaveDto.getCategoryId()).orElseThrow(
                ()-> new RuntimeException("category not found")
        ));

        return new ProductDto(productRepository.save(product));
    }

    public ProductDto findByProductTitle(String title){
      return   productRepository.findByTitle(title)
              .orElseThrow(ProductNotFoundException::new);
    }

public Optional<Product> findById(Long id){
        return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new));
}

public Optional<List<ProductDto>>findAllByProductTitle(String title){
        return Optional.ofNullable(productRepository.findAllByTitle(title).orElseThrow(
                ProductNotFoundException::new
        ));
}

}
