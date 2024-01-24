package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.enitiy.Authentication;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Optional<Authentication> findByToken(String token);

    void deleteAuthenticationByUserId(Long userId);
}