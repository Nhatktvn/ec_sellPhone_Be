package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.LoginDTO;
import com.nhomA.mockproject.exception.AccountNotActiveException;
import com.nhomA.mockproject.service.LoginService;
import com.nhomA.mockproject.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/login")
@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final LoginService loginService;
    public LoginController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            //Authenticate
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            //Generate JWT TOKEN
            boolean accountActived = loginService.checkActiveAccount(loginDTO.getUsername());
            final String token = JwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch(BadCredentialsException ex) {
            return new ResponseEntity<>("account or password is incorrect", HttpStatus.valueOf(401));
        }
        catch (AccountNotActiveException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
