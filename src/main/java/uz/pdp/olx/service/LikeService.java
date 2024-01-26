package uz.pdp.olx.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.LikeDto;
import uz.pdp.olx.dto.LikeSaveDto;
import uz.pdp.olx.enitiy.Like;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.repository.LikeRepository;
import uz.pdp.olx.repository.ProductRepository;
import uz.pdp.olx.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();



    public LikeDto getLikeById(Long id) {
        Like optionalLike = likeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like not found"));
      return   new LikeDto(optionalLike.getId(),optionalLike.getUser().getId(),optionalLike.getProduct().getId());

    }

    public List<LikeDto> getLikesByUserId(Long userId) {
        List<Like> likes = likeRepository.findByUserId(userId);
        return likes.stream()
                .map(like -> new LikeDto(like.getId(),like.getUser().getId(),like.getProduct().getId()))
                .collect(Collectors.toList());
    }

    public List<LikeDto> getLikesByProductId(Long productId) {
        List<Like> likes = likeRepository.findByProductId(productId);
        return likes.stream()
                .map(like -> new LikeDto(like.getId(),like.getUser().getId(),like.getProduct().getId()))
                .collect(Collectors.toList());
    }

    public LikeDto addLike(LikeSaveDto likeSaveDto) {
        // userId orqali User obyektini olish
        User user = userRepository.findById(likeSaveDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + likeSaveDto.getUserId()));

        // productId orqali Product obyektini olish
        Product product = productRepository.findById(likeSaveDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + likeSaveDto.getProductId()));

        // Like obyektini shakllantirish
        Like like = new Like();
        like.setUser(user);
        like.setProduct(product);

        // Saqlash
        Like addedLike = likeRepository.save(like);

        // LikeDto ga o'zgartirish
        return new LikeDto(like.getId(),like.getUser().getId(),like.getProduct().getId());
    }

    public LikeDto updateLike(Long id, LikeDto likeDto) {
        Optional<Like> optionalLike = likeRepository.findById(id);
        if (optionalLike.isPresent()) {
            Like oldLike = optionalLike.get();
            modelMapper.map(likeDto, oldLike);
            Like updatedLike = likeRepository.save(oldLike);
            return new LikeDto(oldLike.getId(),oldLike.getUser().getId(),oldLike.getProduct().getId());
        }
        return null;
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }


}
