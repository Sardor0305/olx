package uz.pdp.olx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.olx.enam.ItemCondition;
import uz.pdp.olx.enitiy.Category;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String title;

    private String description;

    private UserDto user;

    private ItemCondition itemCondition;

    private Double price;

    private Boolean isActive;

    private Category category;

    public ProductDto(Product product) {
        this.title=product.getTitle();
        this.description=product.getDescription();
        this.isActive= product.getIsActive();
        this.price=product.getPrice();
        this.user = new UserDto(product.getUser());
        this.category = product.getCategory();
    }
}

