package uz.pdp.olx.dto.productdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.olx.enam.ItemCondition;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveDto {
    private String title;

    private String description;

    private Long userId;

    private Long categoryId;

    private ItemCondition itemCondition;

    private Double price;

    private Boolean isActive;




}
