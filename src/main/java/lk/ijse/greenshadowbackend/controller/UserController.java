package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.UserDto;
import lk.ijse.greenshadowbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {

        List<UserDto> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @PutMapping("/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {

        userService.updateUser( userDto);
        return ResponseEntity.ok("User updated successfully");
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted successfully");
    }
}
