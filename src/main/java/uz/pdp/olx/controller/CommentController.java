package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olx.dto.CommentDto;
import uz.pdp.olx.dto.CommentSaveDto;
import uz.pdp.olx.dto.CommentUpdateDto;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.service.CommentService;
import uz.pdp.olx.service.ProductService;
import uz.pdp.olx.service.UserService;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<CommentDto> save (@RequestBody final CommentSaveDto commentSaveDto){
        return ResponseEntity.ok(commentService.saveComment(commentSaveDto));
    }


    @PostMapping("/update")
    public ResponseEntity<CommentDto> update (@RequestBody final CommentUpdateDto commentUpdateDto){
        return ResponseEntity.ok(commentService.updateComment(commentUpdateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.deleteCommentById(id));
    }


}
