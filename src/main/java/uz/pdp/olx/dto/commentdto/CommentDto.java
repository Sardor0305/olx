package uz.pdp.olx.dto.commentdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    @NotNull
    private String text;
    @NotBlank
    @NotNull
    private Long userId;
    @NotBlank
    @NotNull
    private Long productId;

    public CommentDto(Comment comment) {
        this.text = comment.getText();
        this.userId = comment.getUser().getId();
        this.productId =  comment.getProduct().getId();
    }
}
