package uz.pdp.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olx.dto.permissiondto.PermissionDto;
import uz.pdp.olx.dto.permissiondto.PermissionSaveDto;
import uz.pdp.olx.service.PermissionService;
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPermission(@RequestBody PermissionSaveDto permissionDto) {
        return ResponseEntity.ok(permissionService.createPermission(permissionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getPermissionById(@PathVariable Long id) {
        PermissionDto permissionDto = permissionService.getPermissionById(id);
        if (permissionDto != null) {
            return new ResponseEntity<>(permissionDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePermission(@RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionService.updatePermission(permissionDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        boolean deleted = permissionService.deletePermission(id);
        if (deleted) {
            return new ResponseEntity<>("Permission deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Permission not found", HttpStatus.NOT_FOUND);
        }
    }
}