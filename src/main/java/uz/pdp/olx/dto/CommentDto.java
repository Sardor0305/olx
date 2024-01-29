package uz.pdp.olx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.olx.enitiy.Comment;

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
