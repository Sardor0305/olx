package uz.pdp.olx.dto.permissiondto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.olx.enitiy.Permission;

import java.io.Serializable;

/**
 * DTO for {@link Permission}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDto implements Serializable {
     Long id;
     String value;
}