package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.LikeDto;
import uz.pdp.olx.dto.LikeSaveDto;
import uz.pdp.olx.enitiy.Like;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.exception.NullOrEmptyException;
import uz.pdp.olx.repository.LikeRepository;
import uz.pdp.olx.repository.ProductRepository;
import uz.pdp.olx.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();



    public LikeDto getLikeById(Long id) {
        if (id == null) {
            throw new NullOrEmptyException("Id");
        }
        Like optionalLike = likeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Like"));
      return new LikeDto(optionalLike.getId(),optionalLike.getUser().getId(),optionalLike.getProduct().getId());

    }

    public List<LikeDto> getLikesByUserId(Long userId) {
        if (userId == null) {
            throw new NullOrEmptyException("UserId");
        }
        List<Like> likes = likeRepository.findByUserId(userId);
        return likes.stream()
                .map(like -> new LikeDto(like.getId(),like.getUser().getId(),like.getProduct().getId()))
                .collect(Collectors.toList());
    }

    public List<LikeDto> getLikesByProductId(Long productId) {
        if (productId == null) {
            throw new NullOrEmptyException("ProductId");
        }
        List<Like> likes = likeRepository.findByProductId(productId);
        return likes.stream()
                .map(like -> new LikeDto(like.getId(),like.getUser().getId(),like.getProduct().getId()))
                .collect(Collectors.toList());
    }

    public LikeDto addLike(LikeSaveDto likeSaveDto) {

        if (likeSaveDto.getUserId() == null) {
            throw new NullOrEmptyException("UserId");
        }
        if (likeSaveDto.getProductId() == null) {
            throw new NullOrEmptyException("ProductId");
        }

        User user = userRepository.findById(likeSaveDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User"));

        Product product = productRepository.findById(likeSaveDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product"));

        return new LikeDto(likeRepository.save(Like.builder()
                .user(user)
                .product(product)
                .build()));
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }


}
