package uz.pdp.olx.dto.categorydto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ParentSaveCategoryDto {
    private String parentCategoryName;
    private String parentImagePath;
}
