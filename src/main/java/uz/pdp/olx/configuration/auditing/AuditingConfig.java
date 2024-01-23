package uz.pdp.olx.configuration.auditing;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.repository.UserRepository;
import uz.pdp.olx.service.UserService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditingConfig implements AuditorAware<User> {
//Userni Security Authenticationda olish kerak
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of(1L);
    }
}
