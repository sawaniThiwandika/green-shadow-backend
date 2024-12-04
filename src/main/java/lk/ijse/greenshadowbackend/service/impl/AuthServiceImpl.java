package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.UserDao;
import lk.ijse.greenshadowbackend.dto.impl.UserDto;
import lk.ijse.greenshadowbackend.entity.impl.UserEntity;
import lk.ijse.greenshadowbackend.exception.UserNotFoundException;
import lk.ijse.greenshadowbackend.secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.secure.SignIn;
import lk.ijse.greenshadowbackend.service.AuthService;
import lk.ijse.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        UserEntity user = userDao.findByUserEmail(signIn.getEmail()).orElseThrow(() -> new UserNotFoundException("User not Found"));
        String generateToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generateToken).build();

    }

    @Override
    public JWTAuthResponse signUp(UserDto userDto) {
        UserEntity userEntity = mapping.toUserEntity(userDto);
//        save user
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDto));
        String token = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(token).build();
//        generate token and return


    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
//        extract userName
        String user = jwtService.extractUserName(accessToken);
//        check the user availability in the db
        UserEntity findUser = userDao.findByUserEmail(user).orElseThrow(() -> new UserNotFoundException("Cant find User"));
        String refreshToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
