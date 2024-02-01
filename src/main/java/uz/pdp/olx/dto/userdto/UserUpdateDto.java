package uz.pdp.olx.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "Invalid username: Username is NULL")
    private String username;
    private String email;
    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "^\\+\\d{12}$", message = "Invalid phone number")
    private String phoneNumber;
    @Pattern(regexp = ".{8,32}", message = "Invalid password")
    private String oldPassword;
    @Pattern(regexp = ".{9,32}", message = "Invalid password")
    private String newPassword;
}
