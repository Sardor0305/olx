package uz.pdp.olx.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ParentUpdateCategoryDto {
    private Long id;
    private String parentCategoryName;
    private String parentImagePath;
}
