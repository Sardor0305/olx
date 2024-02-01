package uz.pdp.olx.dto.imagedto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.olx.dto.productdto.ProductDto;
import uz.pdp.olx.enitiy.Image;


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
