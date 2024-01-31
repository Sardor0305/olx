package uz.pdp.olx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = ".{8,}",message = "product title more than 8 word")
    private String title;
    @Pattern(regexp = ".{8,}",message = "product title more than 8 word")
    private String description;
    @NotNull
    @NotBlank
    private Long userId;
    @NotNull(message = "category is null")
    @NotBlank(message = "category is blank")
    private Long categoryId;
    @NotNull(message = "item condition is blank")
    @NotBlank(message = "item condition is null")
    private ItemCondition itemCondition;
    @NotNull(message = "price is null")
    @NotBlank(message = "price is blank")
    private Double price;

    private Boolean isActive;




}
