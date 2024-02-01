package uz.pdp.olx.dto.categorydto;


import lombok.*;
import uz.pdp.olx.enitiy.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ParentCategoryDto {
    private Long id;
    private String name;
    private String parentImagePath;
    public ParentCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parentImagePath =category.getParentImagePath();
    }
}
