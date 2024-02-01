package uz.pdp.olx.dto.productdto;

import lombok.*;
import uz.pdp.olx.dto.userdto.UserDto;
import uz.pdp.olx.enam.ItemCondition;
import uz.pdp.olx.enitiy.Category;
import uz.pdp.olx.enitiy.Product;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String title;

    private String description;

    private UserDto user;

    private ItemCondition itemCondition;

    private Double price;

    private Boolean isActive;

    private Category category;

    public ProductDto(Product product) {
        this.id=product.getId();
        this.title=product.getTitle();
        this.description=product.getDescription();
        this.isActive= product.getIsActive();
        this.price=product.getPrice();
        this.user = new UserDto(product.getUser());
        this.category = product.getCategory();
    }

    public ProductDto(Long id) {
        this.id = id;
    }
}

