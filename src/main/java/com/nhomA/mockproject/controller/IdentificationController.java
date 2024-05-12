package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.IdentificationDTO;
import com.nhomA.mockproject.service.IdentificationService;
import com.nhomA.mockproject.service.UploadFileService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/identification")
@CrossOrigin(origins = "http://localhost:3000")
public class IdentificationController {
    private final IdentificationService identificationService;
    private final UploadFileService uploadFileService;

    public IdentificationController(IdentificationService identificationService, UploadFileService uploadFileService) {
        this.identificationService = identificationService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping
    public ResponseEntity<?> getIdentification(Authentication authentication)
    {
        String username = authentication.getName();
        try{
            return new ResponseEntity<> (identificationService.getIdentification(username), HttpStatus.OK);
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
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateIdentification(Authentication authentication, @RequestParam(value = "image", required = false) MultipartFile multipartFile,
                                                  @RequestParam("fullName") String fullName, @RequestParam("birthDate") LocalDate birthDate,
                                                  @RequestParam("phone") String phone, @RequestParam("email") String email
                                                  ) throws IOException {
        String imageUrl = "";
        if(multipartFile != null){
            imageUrl = uploadFileService.uploadFile(multipartFile);
        }
        String username = authentication.getName();
        IdentificationDTO identificationDTO = new IdentificationDTO(fullName,birthDate,phone,email,imageUrl);
        try{
            return new ResponseEntity<> (identificationService.updateIdentification(username,identificationDTO), HttpStatus.OK);
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
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
