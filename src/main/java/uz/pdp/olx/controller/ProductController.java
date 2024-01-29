package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.dto.ProductSaveDto;
import uz.pdp.olx.dto.ProductUpdateDto;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.service.ProductService;
import uz.pdp.olx.service.UserService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ProductDto> save(@RequestBody final ProductSaveDto productSaveDto){
        return ResponseEntity.ok(productService.save(productSaveDto));
    }

    @GetMapping("/findAllProduct/{title}")
    public ResponseEntity<?> findAllProduct(@PathVariable String title){
        return ResponseEntity.ok(productService.findAllByProductTitle(title));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>deleteById(@PathVariable Long id){
       return ResponseEntity.ok( productService.deleteByProductId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> update(@RequestBody ProductUpdateDto productUpdateDto){
        return  ResponseEntity.ok(productService.updateProduct(productUpdateDto));
    }
    @GetMapping("find/by/id/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findByProductId(id).orElseThrow(() -> new NotFoundException("Product")));
    }



}
