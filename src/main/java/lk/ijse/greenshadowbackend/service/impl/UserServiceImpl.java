package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.customStatusCodes.SelectedUserErrorStatus;
import lk.ijse.greenshadowbackend.dao.UserDao;
import lk.ijse.greenshadowbackend.dto.impl.UserDto;
import lk.ijse.greenshadowbackend.dto.impl.UserStatus;
import lk.ijse.greenshadowbackend.entity.impl.UserEntity;
import lk.ijse.greenshadowbackend.exception.DataPersistException;
import lk.ijse.greenshadowbackend.exception.UserNotFoundException;
import lk.ijse.greenshadowbackend.service.UserService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDto userDto) {
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDto));
        if (savedUser == null) {
            throw new DataPersistException("User Not Found");
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserEntity> all = userDao.findAll();
        return mapping.toUserDtoList(all);
    }

    @Override
    public UserStatus getUser(String userId) {

        if (userDao.existsById(userId)) {
            UserEntity byId = userDao.getReferenceById(userId);
            return mapping.toUserDto(byId);
        } else {
            return new SelectedUserErrorStatus(2, "User with Id " + userId + "not found!!");
        }


    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> exitsUser = userDao.findById(userId);
        if (exitsUser.isEmpty()){
            throw new UserNotFoundException("User Not Found!!");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public void updateUser(UserDto userDto) {
        Optional<UserEntity> tmpUser = userDao.findById(userDto.getUserEmail());
        if (tmpUser.isPresent()) {

            tmpUser.get().setUserEmail(userDto.getUserEmail());
            tmpUser.get().setUserPassword(userDto.getUserPassword());

        }
    }

    @Override
    public UserDetailsService userDetailService() {
        return username ->
                userDao.findByUserEmail(username).orElseThrow(()-> new UserNotFoundException("USER NOT FOUND!!"));
    }
}
