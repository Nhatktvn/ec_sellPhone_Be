package com.nhomA.mockproject.controller;
import com.nhomA.mockproject.service.AuthService;
import com.nhomA.mockproject.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final private AuthService authService;

    public AuthController(AuthService authService ) {
        this.authService = authService;
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        try {
            String username = authentication.getName();
            return new ResponseEntity<>(authService.getRoleUser(username), HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam("token") String token) {
        try{
            if (JwtUtils.isTokenExpired(token)) {
                String refreshedToken = JwtUtils.refreshToken(token);
                return new ResponseEntity<>(refreshedToken, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Token con han", HttpStatus.OK);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
