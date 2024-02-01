package uz.pdp.olx.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.olx.dto.jwtdto.JwtDto;
import uz.pdp.olx.dto.userdto.UserDto;
import uz.pdp.olx.dto.userdto.UserRegisterDto;
import uz.pdp.olx.dto.userdto.UserUpdateDto;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.exception.AlreadyExistsException;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.exception.NullOrEmptyException;
import uz.pdp.olx.repository.PermissionRepository;
import uz.pdp.olx.repository.UserRepository;
import uz.pdp.olx.security.jwt.JwtTokenProvider;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PermissionRepository permissionRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public JwtDto register(final UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            throw new NullOrEmptyException("Username");
        }
        if (userRegisterDto.email() != null) {
            if (userRepository.existsByEmail(userRegisterDto.email()) &&
                    userRepository.existsByUsername(userRegisterDto.username())) {
                throw new AlreadyExistsException("User");
            }
            emailService.sendEmailVerificationMessage(userRegisterDto.username(), userRegisterDto.email());
        }

        var user = new User();
        user.setUsername(userRegisterDto.username());
        user.setPassword(passwordEncoder.encode(userRegisterDto.password()));
        user.setPermissions(List.of(permissionRepository.findByValue("VIEW_PRODUCTS").get()));
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
        if (user.getPassword().equals(userUpdateDto.getOldPassword())) {
            updatedUser.setPassword(userUpdateDto.getNewPassword());
        }
        else {
            throw new PasswordNotMatchException();
        }

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

    public boolean verify(String token) {
        if(token == null) {
            return false;
        }
        Claims claims = jwtTokenProvider.parseAllClaims(token);
        if (jwtTokenProvider.isValid(token)) {
            String email = claims.getSubject();
            String username = claims.get("username", String.class);
            if (userRepository.findByUsername(username).isPresent()) {
                User user = userRepository.findByUsername(username).get();
                user.setEmail(email);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
