package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olx.dto.productdto.ProductReviewDto;
import uz.pdp.olx.service.ProductReviewService;

@RestController
@RequestMapping("/product-review")
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody ProductReviewDto productReviewDto) {
        return ResponseEntity.ok(productReviewService.addReview(productReviewDto));
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.ok(productReviewService.getAllReviews());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete() {
        productReviewService.deleteProductAndOwnerIfMoreThanFiveReviews();
        return ResponseEntity.ok("Deleted");
    }
}
