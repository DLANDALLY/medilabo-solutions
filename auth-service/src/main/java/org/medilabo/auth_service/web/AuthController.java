package org.medilabo.auth_service.web;


import org.medilabo.auth_service.model.AuthRequest;
import org.medilabo.auth_service.model.AuthResponse;
import org.medilabo.auth_service.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        System.out.println("Login controller");

        var token = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        String jwt = jwtService.generateToken(token);

        System.out.println("Login ENDING");

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
