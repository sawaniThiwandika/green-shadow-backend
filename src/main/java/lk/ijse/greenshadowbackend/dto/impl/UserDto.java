package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.SuperDto;
import lk.ijse.greenshadowbackend.entity.UserRole;
import lombok.Data;

@Data
public class UserDto implements SuperDto {
    private String userEmail;
    private String userPassword;
    private UserRole userRole;
}
