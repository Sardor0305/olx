package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.olx.dto.UserDto;
import uz.pdp.olx.dto.UserRegisterDto;
import uz.pdp.olx.dto.UserUpdateDto;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.exception.AlreadyExistsException;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.exception.NullOrEmptyException;
import uz.pdp.olx.repository.AuthenticationRepository;
import uz.pdp.olx.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthenticationRepository authenticationRepository;

    public UserDto register(final UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.email())) {
            throw new RuntimeException("This user already exist");
        }

        var user = new User();
        user.setEmail(userRegisterDto.email());
        user.setPassword(userRegisterDto.password());
        user = userRepository.save(user);
        emailService.sendEmailVerificationMessage(user);
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber());
    }

    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserDto.builder()
                    .id(user.get().getId())
                    .username(user.get().getUsername())
                    .email(user.get().getEmail())
                    .phoneNumber(user.get().getPhoneNumber())
                    .build();
        }
        throw new NotFoundException("User");
    }

    public UserDto update(UserUpdateDto userUpdateDto) {
        if (userUpdateDto == null)
            throw new NullOrEmptyException("CustomerDto");
        if (userUpdateDto.getId() == null)
            throw new NullOrEmptyException("Id");
        if (userUpdateDto.getUsername() != null && userRepository.findByUsername(userUpdateDto.getUsername()).isPresent())
            throw new AlreadyExistsException("Username");
        if (userUpdateDto.getEmail() != null && userRepository.findByEmail(userUpdateDto.getEmail()).isPresent())
            throw new AlreadyExistsException("Email");
        if (userUpdateDto.getPhoneNumber() != null && userRepository.findByPhoneNumber(userUpdateDto.getPhoneNumber()).isPresent())
            throw new AlreadyExistsException("Phone number");
        User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        User updatedUser = User.builder()
                .id(userUpdateDto.getId())
                .username(Objects.requireNonNullElse(userUpdateDto.getUsername(), user.getUsername()))
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .build();
        if (userUpdateDto.getPassword() != null)
            updatedUser.setPassword(userUpdateDto.getPassword());
        if (userUpdateDto.getEmail() != null)
            updatedUser.setEmail(userUpdateDto.getEmail());
        if (userUpdateDto.getPhoneNumber() != null)
            updatedUser.setPhoneNumber(userUpdateDto.getPhoneNumber());


      updatedUser = userRepository.save(updatedUser);
        return UserDto.builder()
                .id(updatedUser.getId())
                .username(updatedUser.getUsername())
                .email(updatedUser.getEmail())
                .phoneNumber(updatedUser.getPhoneNumber())
                .build();
    }


    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        else {
            authenticationRepository.deleteAuthenticationByUserId(id);
            userRepository.delete(userRepository.findById(id)
                    .orElseThrow(
                            () -> new NotFoundException("User")
                    ));
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
