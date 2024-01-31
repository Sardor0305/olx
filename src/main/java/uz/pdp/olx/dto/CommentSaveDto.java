package uz.pdp.olx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDto {
    @NotBlank(message = "comment is blank")
    @NotNull(message = "comment is null")
    private String text;
    private Long userId;
    private Long productId;
}
