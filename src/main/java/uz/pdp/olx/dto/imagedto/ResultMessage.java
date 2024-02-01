package uz.pdp.olx.dto.imagedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {
    public Boolean success;
    public Object object;
}
