package uz.pdp.olx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionSaveDto {
    @NotBlank(message = "permission is blank")
    @NotNull(message = "permission is null")
    private String value;
}
