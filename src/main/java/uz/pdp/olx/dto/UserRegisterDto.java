package uz.pdp.olx.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto{
    @Email(message = "Invalid email")
    private String email;
    @Pattern(regexp = ".{8,32}",message = "Invalid password : Password length between 8 and 32 word ")
    private String password;
}
