package uz.pdp.olx.configuration.auditing;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import uz.pdp.olx.enitiy.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditingConfig implements AuditorAware<User> {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of(1L);
    }
}
