package uz.pdp.olx.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryUpdateDto {
    private Long id;
    private Long parentCategoryId ;
    private String name;

}
