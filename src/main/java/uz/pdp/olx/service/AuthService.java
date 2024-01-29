package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.olx.enitiy.Authentication;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.exception.TokenIsExpiredException;
import uz.pdp.olx.exception.TokenNotFoundException;
import uz.pdp.olx.jwt.JwtTokenProvider;
import uz.pdp.olx.repository.AuthenticationRepository;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationRepository authenticationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void validateUserAuthentication(final String token) {
       final var authentication = authenticationRepository.findByToken(token).orElseThrow(
                () -> TokenNotFoundException.byToken(token)
        );
        final var userNotFound = authenticationRepository.findById(authentication.getUser().getId()).orElseThrow(
                () -> new NotFoundException("User")
        );

        if (authentication.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw TokenIsExpiredException.expiryDate();
        }

        System.out.println(token);
    }

    public Authentication createAuthentication(User user){
        Authentication authentication = new Authentication();
        authentication.setCreationTime(LocalDateTime.now());
        authentication.setExpirationTime(LocalDateTime.now().plusHours(1));
        authentication.setUser(user);
        authentication.setToken(jwtTokenProvider.generateToken(user));
        return authenticationRepository.save(authentication);
    }
}
