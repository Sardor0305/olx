package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.dto.ProductSaveDto;
import uz.pdp.olx.dto.ProductUpdateDto;
import uz.pdp.olx.dto.UserDto;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.exception.NullOrEmptyException;
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
        product.setUser(userRepository.findById(productSaveDto.getUserId()).orElseThrow(()-> new NotFoundException("User")));
        product.setCategory(categoryRepository.findById(productSaveDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category")));
        productRepository.save(product);
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                new UserDto(product.getUser()),
                product.getItemCondition(),
                product.getPrice(),
                product.getIsActive(),
                product.getCategory());
    }



public Optional<ProductDto> findByProductId(Long id){
        if (id == null) {
            throw new NullOrEmptyException("Id");
        }
        return Optional.ofNullable(productRepository.finBYdId(id).orElseThrow(
                () -> new NotFoundException("Product")
        ));

}


public Optional<List<ProductDto>>findAllByProductTitle(String title){
    if (title == null) {
        throw new NullOrEmptyException("Title");
    }
        return Optional.ofNullable(productRepository.findAllByTitle(title).orElseThrow(
                () -> new NotFoundException("Product")
        ));
}
public Boolean deleteByProductId(Long id){
        if (id == null) {
            throw new NullOrEmptyException("Id");
        }
        if(productRepository.existsById(id)){
             productRepository.deleteById(id);
             return true;
        }
        else {
            throw new NotFoundException("Product");
        }
}
public ProductDto updateProduct(ProductUpdateDto productUpdateDto){
        if (productUpdateDto.getTitle() == null) {
            throw new NullOrEmptyException("Product Title");
        }
        if (productUpdateDto.getDescription() == null) {
            throw new NullOrEmptyException("Product Description");
        }
        if (productUpdateDto.getPrice() == null) {
            throw new NullOrEmptyException("Product Price");
        }
        if (productUpdateDto.getCategoryId() == null) {
            throw new NullOrEmptyException("Product CategoryId");
        }
    Product product = productRepository.findById(productUpdateDto.getId())
            .orElseThrow(() -> new NotFoundException("Product"));
    product.setTitle(productUpdateDto.getTitle());
    product.setDescription(productUpdateDto.getDescription());
    product.setIsActive(productUpdateDto.getIsActive());
    product.setPrice(productUpdateDto.getPrice());
    product.setItemCondition(productUpdateDto.getItemCondition());
    product.setCategory(categoryRepository.findById(productUpdateDto.getCategoryId())
            .orElseThrow(() -> new NotFoundException("Category")));
    return new ProductDto(product);
}

}
