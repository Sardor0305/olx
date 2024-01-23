package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.UserDto;
import uz.pdp.olx.dto.UserRegisterDto;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserDto register(final UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.email())) {
            throw new RuntimeException("This user already exist");
        }

        var user = new User();
        user.setEmail(userRegisterDto.email());
        user.setPassword(userRegisterDto.password());
        user = userRepository.save(user);
        emailService.sendEmailVerificationMessage(user);
        return new UserDto(user.getId(), user.getEmail());
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }
}
