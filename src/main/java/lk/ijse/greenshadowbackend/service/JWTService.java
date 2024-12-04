package lk.ijse.greenshadowbackend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token,UserDetails userDetails);
    String refreshToken(UserDetails userDetails3);
}
