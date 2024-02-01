package uz.pdp.olx.dto.productdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReviewDto implements Serializable {
    String text;
    NestedProductDto product;
    NestedUserDto user;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NestedProductDto implements Serializable {
        Long id;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NestedUserDto implements Serializable {
        Long id;
    }
}