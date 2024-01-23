package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.dto.ProductSaveDto;
import uz.pdp.olx.service.ProductService;
import uz.pdp.olx.service.UserService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ProductDto> signUp(@RequestBody final ProductSaveDto productSaveDto){
        return ResponseEntity.ok(productService.save(productSaveDto));
    }

    @GetMapping("/findAllProduct")
    public ResponseEntity<?> findAllProduct(@RequestParam String title){
        return ResponseEntity.ok(productService.findAllByProductTitle(title));
    }
}
