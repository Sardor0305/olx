package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olx.dto.userdto.UserRegisterDto;
import uz.pdp.olx.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody final UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.register(userRegisterDto));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam String token){
        if (userService.verify(token)) {
            return ResponseEntity.ok("Email verified");
        }
        return ResponseEntity.badRequest().body("Authentication Failed");
    }
}
