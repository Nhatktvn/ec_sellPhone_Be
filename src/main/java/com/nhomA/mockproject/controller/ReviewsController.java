package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.ReviewRequestDTO;
import com.nhomA.mockproject.exception.ProductNotFoundException;
import com.nhomA.mockproject.exception.ReviewNotFoundException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.service.ReviewsService;
import com.nhomA.mockproject.service.UploadFileService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ReviewsController {
    private final ReviewsService reviewsService;
    private final UploadFileService uploadFileService;
    public ReviewsController(ReviewsService reviewsService, UploadFileService uploadFileService) {
        this.reviewsService = reviewsService;
        this.uploadFileService = uploadFileService;
    }

    @PostMapping("/user/reviews/create-review")
    public ResponseEntity<?> createReview(Authentication authentication, @RequestParam("idProduct") Long idProduct,
                                          @RequestParam("rate") int rate, @RequestParam("contentReview") String contentReview, @RequestParam(value = "image", required = false) MultipartFile image){
        try {
            String imageUrl = "";
            if(image != null){
                imageUrl = uploadFileService.uploadFile(image);
            }
            String username = authentication.getName();
            ReviewRequestDTO reviewRequestDTO = new ReviewRequestDTO(idProduct,username,rate,contentReview,imageUrl);
            return new ResponseEntity<>(reviewsService.createReview(reviewRequestDTO), HttpStatus.CREATED);
        }
        catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/user/review/{idReview}")
    public ResponseEntity<?> createReview(Authentication authentication, @PathVariable("idReview") Long idReview, @RequestParam("idProduct") Long idProduct,
                                          @RequestParam("rate") int rate, @RequestParam("contentReview") String contentReview, @RequestParam(value = "image", required = false) MultipartFile image){
        try {
            String imageUrl = "";
            if(image != null){
                imageUrl = uploadFileService.uploadFile(image);
            }
            String username = authentication.getName();
            ReviewRequestDTO reviewRequestDTO = new ReviewRequestDTO(idProduct,username,rate,contentReview,imageUrl);
            return new ResponseEntity<>(reviewsService.updateReview(username,idReview,reviewRequestDTO), HttpStatus.OK);
        }
        catch (ReviewNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/review/{idReview}")
    public ResponseEntity<?> createReview(Authentication authentication, @PathVariable("idReview") Long idReview){
        try {
            String username = authentication.getName();
            return new ResponseEntity<>(reviewsService.deleteReview(username,idReview), HttpStatus.OK);
        }
        catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/reviews/{idProduct}")
    public ResponseEntity<?> createReview(@PathVariable("idProduct") Long idProduct){
        try {
            return new ResponseEntity<>(reviewsService.getAllReviewsByIdProduct(idProduct), HttpStatus.OK);
        }
        catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
