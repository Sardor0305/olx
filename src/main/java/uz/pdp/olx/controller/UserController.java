package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olx.dto.UserUpdateDto;
import uz.pdp.olx.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class
UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.update(userUpdateDto));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
