package uz.pdp.olx.dto.likedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikeSaveDto {
    private Long productId;
    private Long userId;
}
