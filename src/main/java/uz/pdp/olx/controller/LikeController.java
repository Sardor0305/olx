package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olx.dto.likedto.LikeDto;
import uz.pdp.olx.dto.likedto.LikeSaveDto;
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

    @DeleteMapping("/delete/{id}")
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
    }
}
