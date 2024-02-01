package uz.pdp.olx.dto.categorydto;

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
