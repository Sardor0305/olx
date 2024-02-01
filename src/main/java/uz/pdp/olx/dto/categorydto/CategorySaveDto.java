package uz.pdp.olx.dto.categorydto;

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
