package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.Token;
import lk.ijse.greenshadowbackend.dto.impl.UserDto;
import lk.ijse.greenshadowbackend.entity.UserRole;
import lk.ijse.greenshadowbackend.exception.DataPersistException;
import lk.ijse.greenshadowbackend.secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.secure.SignIn;
import lk.ijse.greenshadowbackend.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthUserController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping(value = "/signUp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signUp(
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("role") String role

    ) {

        try {


            var buildUserDto = new UserDto();
            buildUserDto.setUserEmail(email);
            buildUserDto.setUserPassword(passwordEncoder.encode(password));

            buildUserDto.setUserRole(UserRole.valueOf(role));
            return ResponseEntity.ok(authService.signUp(buildUserDto));
//            Todo: Chane with auth user service

        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @PostMapping(value = "/signIn",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody() SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }

    @PostMapping(value = "/refresh",consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody() Token token){
        System.out.println(token.getToken());
        return ResponseEntity.ok(authService.refreshToken(token.getToken()));
    }


  }
