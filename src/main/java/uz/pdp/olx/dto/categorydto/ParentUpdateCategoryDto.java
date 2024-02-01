package uz.pdp.olx.dto.categorydto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ParentUpdateCategoryDto {
    @NotBlank
    @NotNull
    private Long id;
    @NotBlank
    @NotNull
    private String parentCategoryName;
    @NotBlank
    @NotNull
    private String parentImagePath;
}
