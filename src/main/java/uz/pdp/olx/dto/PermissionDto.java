package uz.pdp.olx.dto;

import lombok.Value;
import uz.pdp.olx.enitiy.Permission;

import java.io.Serializable;

/**
 * DTO for {@link Permission}
 */
@Value
public class PermissionDto implements Serializable {
     Long id;
     String value;
}