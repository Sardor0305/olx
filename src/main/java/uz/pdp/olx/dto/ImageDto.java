package uz.pdp.olx.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import uz.pdp.olx.enitiy.Image;
import uz.pdp.olx.enitiy.Product;


@Getter
@Setter

@NoArgsConstructor

public class ImageDto {
    private Long id;
    private String imagePath;
    private String contentType;
    private Long product_id;

    public ImageDto(Image image){
        this.id = image.getId();
        this.imagePath = image.getImagePath();
        this.contentType = image.getContentType();
        this.product_id = new ProductDto(image.getProduct()).getId();
    }
}
