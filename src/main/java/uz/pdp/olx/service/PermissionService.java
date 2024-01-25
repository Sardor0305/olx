package uz.pdp.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.olx.dto.PermissionDto;
import uz.pdp.olx.dto.PermissionSaveDto;
import uz.pdp.olx.enitiy.Permission;
import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.exception.NullOrEmptyException;
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


    public PermissionDto createPermission(PermissionSaveDto permissionSaveDto) {
        if(permissionSaveDto == null) {
            throw new NullOrEmptyException("Permission ");
        }
       return PermissionDto.builder()
                .value(permissionSaveDto.getValue())
                .build();
    }


    public PermissionDto getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Permission "));
        return permission != null ? new PermissionDto(permission.getId(), permission.getValue()) : null;
    }


    public List<PermissionDto> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permission -> new PermissionDto(permission.getId(), permission.getValue()))
                .collect(Collectors.toList());
    }


    public PermissionDto updatePermission(PermissionDto permissionDto) {
        Permission existingPermission = permissionRepository.findById(permissionDto.getId())
                .orElseThrow(() -> new NotFoundException("Permission "));

        if (existingPermission != null) {
            return PermissionDto.builder()
                    .id(permissionDto.getId())
                    .value(permissionDto.getValue())
                    .build();
        }
        return null;
    }


    public Boolean deletePermission(Long id) {
        Permission existingPermission = permissionRepository.findById(id).orElse(null);
        if (existingPermission != null) {
            permissionRepository.delete(existingPermission);
            return true;
        }
        return false;
    }
}
