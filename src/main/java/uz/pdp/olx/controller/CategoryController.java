package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olx.dto.*;
import uz.pdp.olx.enitiy.Category;
import uz.pdp.olx.service.CategoryService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/save/parent/category")
    public ResponseEntity<?> parentSaveCategory(@RequestBody ParentSaveCategoryDto parentCategory){
        return ResponseEntity.ok(categoryService.saveParentCategory(parentCategory));

    }
@GetMapping("/find/parent/category/{id}")
    public ResponseEntity<?> findParentCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findParentCategoryById(id));
}

@PutMapping("/update/parent/category")
    public ResponseEntity<?> updateParentCategory(@RequestBody ParentUpdateCategoryDto parentUpdateCategoryDto){
        return ResponseEntity.ok(categoryService.updateParentCategory(parentUpdateCategoryDto));
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.delete(id));
}
    @PostMapping("/save/category")
    public ResponseEntity<?> parentCategory(@RequestBody CategorySaveDto categorySaveDto){
        return ResponseEntity.ok(categoryService.saveCategory(categorySaveDto));

    }

    @PutMapping("/update/category")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto){
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryUpdateDto));
    }

    @GetMapping("/find/all/category/{id}")
    public ResponseEntity<?> findAllCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findAllCategoryById(id));
    }
    @GetMapping("/find/parent/category/{parentId}")
    public ResponseEntity<?> findCategoryByParentId(@PathVariable Long parentId) {
        return ResponseEntity.ok(categoryService.findAllCategoryById(parentId));
    }

    @GetMapping
    public ResponseEntity<List<Category>> f(){
        return ResponseEntity.ok(categoryService.all());


    }







}
