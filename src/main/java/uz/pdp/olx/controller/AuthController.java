package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olx.dto.UserRegisterDto;
import uz.pdp.olx.service.AuthService;
import uz.pdp.olx.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody final UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.register(userRegisterDto));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam String token){
        authService.validateUserAuthentication(token);
        return ResponseEntity.ok("Email verified");
    }
}
