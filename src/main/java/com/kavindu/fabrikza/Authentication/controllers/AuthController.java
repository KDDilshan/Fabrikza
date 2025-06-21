package com.kavindu.fabrikza.Authentication.controllers;

import com.kavindu.fabrikza.Authentication.Dto.Request.*;
import com.kavindu.fabrikza.Authentication.Dto.Response.UserProfileResponse;
import com.kavindu.fabrikza.Authentication.models.AppUser;
import com.kavindu.fabrikza.Authentication.services.ImageHandlingUserService;
import com.kavindu.fabrikza.Authentication.services.JwtService;
import com.kavindu.fabrikza.Authentication.services.UpdateUserService;
import com.kavindu.fabrikza.Authentication.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/v1")
public class AuthController {
    private final JwtService jwtService;
    private final UserService userService;
    private final UpdateUserService updateUserService;
    private final ImageHandlingUserService imageHandlingUserService;
    private final static Logger logger= LoggerFactory.getLogger(AuthController.class);

    public AuthController(JwtService jwtService, UserService userService, UpdateUserService updateUserService, ImageHandlingUserService imageHandlingUserService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.updateUserService = updateUserService;
        this.imageHandlingUserService = imageHandlingUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> RegisterUser(@RequestBody RegisterDto registerDto) {
        try{
            AppUser registeredUser=userService.RegisterUser(registerDto);
            return ResponseEntity.ok(registeredUser);
        }catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginDto loginDto) {
        try{
            return ResponseEntity.ok(userService.loginUser(loginDto));
        }catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/admin_Register")
    public ResponseEntity<?> RegisterAdmin(@RequestBody RegisterDto registerDto) {
        try{
            AppUser registerdAdmin=userService.RegisterAdmin(registerDto);
            return ResponseEntity.ok(registerdAdmin);
        }catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/Refresh")
    public ResponseEntity<?> getAcessToken(@RequestBody TokenRequest request) {
        try {
            String refreshToken=request.getRefresh_token();
            if(refreshToken==null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("refresh token is null");
            }
            Map<String,String> tokens=userService.getAccessToken(refreshToken);
            return ResponseEntity.ok(tokens);
        }catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> UpdateUserDetails(@PathVariable Integer id, @RequestBody AppUser appUser){
        UserUpdate userUpdate=new UserUpdate();
        userUpdate.setId(id);
        userUpdate.setAppUser(appUser);
        return updateUserService.update(userUpdate);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestHeader("Authorization") String token) {

        String actualToken = token.replace("Bearer ", "").trim();
        String userEmail = jwtService.extractUsername(actualToken);

        logger.info("geting info about user [%s]".formatted(userEmail));

        UserProfileResponse profile = userService.getUserByUserEmail(userEmail);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try{
            userService.logout(request);
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        }catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping(value = "/user-image/upload/{userId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadUserImage(@PathVariable("userId")Integer userId,
                                   @RequestParam("file") MultipartFile file){
        try{
            imageHandlingUserService.uploadProductImage(userId,file);
            return ResponseEntity.status(HttpStatus.OK).body("image upload sucessfully");
        }catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }

    @GetMapping("/user-image/{userId}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable("userId") Integer userId) {

        byte[] image = imageHandlingUserService.getUserImage(userId);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }




}
