package uz.pdp.olx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDto {
    private String text;
    private Long userId;
    private Long productId;
}
