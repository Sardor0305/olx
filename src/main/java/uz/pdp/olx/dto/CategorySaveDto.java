package uz.pdp.olx.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategorySaveDto {
    private Long parentCategoryId ;
    private String name;

}
