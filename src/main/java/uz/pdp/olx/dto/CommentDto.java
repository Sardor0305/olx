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
    private UserDto user;
    private ProductDto product;

    public CommentDto(Comment comment) {
        this.text = comment.getText();
        this.user = new UserDto(comment.getUser());
        this.product = new ProductDto(comment.getProduct());
    }
}
