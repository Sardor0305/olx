package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olx.dto.LikeDto;
import uz.pdp.olx.dto.LikeSaveDto;
import uz.pdp.olx.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{id}")
    public LikeDto getLikeById(@PathVariable Long id) {
        return likeService.getLikeById(id);
    }

    @GetMapping("/user/{userId}")
    public List<LikeDto> getLikesByUserId(@PathVariable Long userId) {
        return likeService.getLikesByUserId(userId);
    }

    @GetMapping("/product/{productId}")
    public List<LikeDto> getLikesByProductId(@PathVariable Long productId) {
        return likeService.getLikesByProductId(productId);
    }

    @PostMapping("/add")
    public LikeDto addLike(@RequestBody LikeSaveDto likeSaveDto) {
        return likeService.addLike(likeSaveDto);
    }

    @PutMapping("/update/{id}")
    public LikeDto updateLike(@PathVariable Long id, @RequestBody LikeDto likeDto) {
        return likeService.updateLike(id, likeDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
    }
}
