package uz.pdp.olx.dto;

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
