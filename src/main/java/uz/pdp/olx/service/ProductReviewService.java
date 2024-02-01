package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.olx.dto.productdto.ProductReviewDto;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.ProductReview;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.exception.AlreadyExistsException;
import uz.pdp.olx.exception.NullOrEmptyException;
import uz.pdp.olx.repository.ProductRepository;
import uz.pdp.olx.repository.ProductReviewRepository;
import uz.pdp.olx.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductReviewDto addReview(final ProductReviewDto productReviewDto) {
        if (productReviewDto.getText() == null) {
            throw new NullOrEmptyException("Review Text");
        }
        if (productReviewDto.getUser() == null) {
            throw new AlreadyExistsException("Review UserId");
        }
        if (productReviewDto.getProduct() == null) {
            throw new NullOrEmptyException("Review ProductId");
        }

       ProductReview productReview = productReviewRepository.save(
                ProductReview.builder()
                        .text(productReviewDto.getText())
                        .product(productRepository.findById(productReviewDto.getProduct().getId()).get())
                        .user(userRepository.findById(productReviewDto.getUser().getId()).get())
                        .build()
        );

        return ProductReviewDto.builder()
                .text(productReview.getText())
                .product(new ProductReviewDto.NestedProductDto(productReview.getProduct().getId()))
                .user(new ProductReviewDto.NestedUserDto(productReview.getUser().getId()))
                .build();
    }

    public List<ProductReviewDto> getAllReviews() {
        return productReviewRepository.findAll().stream()
                .map(v -> ProductReviewDto.builder()
                        .text(v.getText())
                        .product(new ProductReviewDto.NestedProductDto(v.getProduct().getId()))
                        .user(new ProductReviewDto.NestedUserDto(v.getUser().getId()))
                        .build())
                .toList();
    }

    @Transactional
    public void deleteProductAndOwnerIfMoreThanFiveReviews() {
        List<Product> productsToDelete = productRepository.findProductsWithMoreThanFiveReviews();

        if(productsToDelete == null) {
            throw new NullOrEmptyException("Products");
        } else {
            for (Product product : productsToDelete) {
                User owner = product.getUser();
                productRepository.deleteWithCascade(product.getId());
                userRepository.deleteWithCascade(owner.getId());
            }
        }
    }
}
