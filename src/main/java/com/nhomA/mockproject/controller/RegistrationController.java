package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.RegistrationDTO;
import com.nhomA.mockproject.exception.RoleNotFoundException;
import com.nhomA.mockproject.exception.UserNameExistedException;
import com.nhomA.mockproject.service.RegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationDTO) {
        try {
            registrationService.registration(registrationDTO);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (UserNameExistedException | RoleNotFoundException ex ) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(DataIntegrityViolationException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
