package uz.pdp.olx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.olx.enam.ItemCondition;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.SubCategory;
import uz.pdp.olx.enitiy.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String title;

    private String description;

    private User user;

    private ItemCondition ItemCondition;

    private Double price;

    private Boolean isActive;

    private Integer rate;

    private SubCategory subCategory;

    public ProductDto(Product product) {
        this.title=product.getTitle();
        this.description=product.getDescription();
        this.isActive= product.getIsActive();
        this.price=product.getPrice();
        this.user = product.getUser();
        this.subCategory = product.getSubCategory();
    }
}

