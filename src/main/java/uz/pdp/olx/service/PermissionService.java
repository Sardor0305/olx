package uz.pdp.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.PermissionDto;
import uz.pdp.olx.enitiy.Permission;
import uz.pdp.olx.repository.PermissionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    public void createPermission(PermissionDto permissionDto) {
        Permission permission = new Permission();
        permission.setValue(permissionDto.getValue());
        permissionRepository.save(permission);
    }


    public PermissionDto getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        return permission != null ? new PermissionDto(permission.getId(), permission.getValue()) : null;
    }


    public List<PermissionDto> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permission -> new PermissionDto(permission.getId(), permission.getValue()))
                .collect(Collectors.toList());
    }


    public boolean updatePermission(Long id, PermissionDto permissionDto) {
        Permission existingPermission = permissionRepository.findById(id).orElse(null);
        if (existingPermission != null) {
            existingPermission.setValue(permissionDto.getValue());
            permissionRepository.save(existingPermission);
            return true;
        }
        return false;
    }


    public boolean deletePermission(Long id) {
        Permission existingPermission = permissionRepository.findById(id).orElse(null);
        if (existingPermission != null) {
            permissionRepository.delete(existingPermission);
            return true;
        }
        return false;
    }
}
