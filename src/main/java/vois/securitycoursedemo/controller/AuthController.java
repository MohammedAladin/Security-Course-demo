package vois.securitycoursedemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vois.securitycoursedemo.security.jwt.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;


    public AuthController(JwtUtils jwtUtils, AuthenticationConfiguration authConfig) throws Exception {
        this.jwtUtils = jwtUtils;
        this.authManager = authConfig.getAuthenticationManager();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestParam String username, @RequestParam String password) {
        var auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = jwtUtils.generateToken(username);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }


    public record JwtAuthResponse(String accessToken, String tokenType) {
        public JwtAuthResponse(String accessToken) {
            this(accessToken, "Bearer");
        }
    }


}
