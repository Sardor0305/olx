package uz.pdp.olx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.olx.enitiy.Like;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDto {
    private Long id;
    private Long product;
    private Long user;

    public LikeDto(Like like) {
        this.product = like.getProduct().getId();
        this.user = like.getUser().getId();
    }
}
