package com.flutter.project_flutter.services;

import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.entites.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserServices {
    public UserDto register(UserDto user);
    public List<UserDto> getAllUsers();
    public UserDto getOneUser(int id);
    public void deleteUser(int id);

    UserDto updateUser(UserDto userDto, int id);
    UserDto registerByAdmin(UserDto user);
}
