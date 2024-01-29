package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.CommentDto;
import uz.pdp.olx.dto.CommentSaveDto;
import uz.pdp.olx.dto.CommentUpdateDto;
import uz.pdp.olx.dto.ProductDto;
import uz.pdp.olx.dto.UserDto;
import uz.pdp.olx.enitiy.Comment;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.repository.CommentRepository;
import uz.pdp.olx.repository.ProductRepository;
import uz.pdp.olx.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentDto saveComment(CommentSaveDto commentSaveDto){
        Comment comment = new Comment();

        comment.setText(commentSaveDto.getText());
        comment.setUser(userRepository.findById(commentSaveDto.getUserId())
                .orElseThrow( () -> new NotFoundException("User")));
        comment.setProduct(productRepository.findById(commentSaveDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product")));
        commentRepository.save(comment);
        return new CommentDto(
                 comment.getText(),
                 comment.getUser().getId(),
                comment.getProduct().getId());
    }

    public CommentDto updateComment(CommentUpdateDto commentUpdateDto){
        Comment comment = commentRepository.findById(commentUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException("Product"));

        comment.setText(commentUpdateDto.getText());
        commentRepository.save(comment);
        return new CommentDto(comment.getText(),
                comment.getUser().getId(),
                comment.getProduct().getId());

    }

    public Boolean deleteCommentById(Long id){
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            return true;
        }else {
            throw new NotFoundException("Comment");

        }
    }

}

