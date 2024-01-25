package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olx.enitiy.Permission;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByValue(String value);
}