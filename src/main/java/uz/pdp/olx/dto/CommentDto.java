package uz.pdp.olx.dto;

import lombok.*;
import uz.pdp.olx.enitiy.Comment;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private String text;
    private Long userId;
    private Long productId;

    public CommentDto(Comment comment) {
        this.text = comment.getText();
        this.userId = comment.getUser().getId();
        this.productId =  comment.getProduct().getId();
    }
}
