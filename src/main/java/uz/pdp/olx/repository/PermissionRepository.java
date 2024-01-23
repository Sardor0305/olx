package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olx.enitiy.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}