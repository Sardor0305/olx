package uz.pdp.olx.dto.categorydto;

import lombok.*;
import uz.pdp.olx.enitiy.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDto {
    private Long id;
    private String name;
    private ParentCategoryDto parentCategory;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parentCategory = new ParentCategoryDto(category.getParentCategory());

    }
}
