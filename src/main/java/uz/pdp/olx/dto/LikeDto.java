package uz.pdp.olx.dto;

import lombok.*;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.User;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDto {
    private Long id;
    private Long product;
    private Long user;
}
