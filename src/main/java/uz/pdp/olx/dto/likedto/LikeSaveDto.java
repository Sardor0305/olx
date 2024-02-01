package uz.pdp.olx.dto.likedto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikeSaveDto {
    @NotBlank
    @NotNull
    private Long productId;
    @NotBlank
    @NotNull
    private Long userId;
}
