package uz.pdp.olx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
