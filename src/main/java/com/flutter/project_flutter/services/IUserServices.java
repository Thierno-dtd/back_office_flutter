package com.flutter.project_flutter.services;

import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface IUserServices {
    public UserDto register(UserDto user);
    public List<UserDto> getAllUsers();
    public UserDto getOneUser(int id) throws EntityNotFoundException;
    public void deleteUser(int id);

    UserDto updateUser(UserDto userDto, int id);
    UserDto registerByAdmin(UserDto user);
    UserDto rechargeSolde(int id, BigDecimal somme);
}
