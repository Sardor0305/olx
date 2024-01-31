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
public class CommentUpdateDto {
    @NotBlank
    @NotNull
    private Long id;
    @NotBlank(message = "comment is blank")
    @NotNull(message = "comment is null")
    private String text;


}
