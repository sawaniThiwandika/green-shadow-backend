package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.UserDto;
import lk.ijse.greenshadowbackend.dto.impl.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    List<UserDto> getAllUser();
    UserStatus getUser(String userId);
    void deleteUser(String userId);
    void updateUser(UserDto userDto);
    UserDetailsService userDetailService();
}
