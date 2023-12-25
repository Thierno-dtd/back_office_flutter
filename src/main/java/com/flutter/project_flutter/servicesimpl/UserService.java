package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.constants.TypeRoles;
import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.entites.User;
import com.flutter.project_flutter.mappers.ApplicationMappers;
import com.flutter.project_flutter.repositories.UserRepository;
import com.flutter.project_flutter.services.IUserServices;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
@Slf4j
public class UserService implements IUserServices {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private ApplicationMappers applicationMappers;

    @Override
    public UserDto register(UserDto userDto) {
        return applicationMappers.convertEntityToDto(
                usersRepository.save(applicationMappers.convertDtoToEntity(userDto))
        );
    }

    @Override
    public UserDto registerByAdmin(UserDto userDto) {
        if(userDto==null){
            log.error("user pass is not valid");
        }
        User user =  applicationMappers.convertDtoToEntity(userDto);
        user.setRoles(
                Collections
                        .singleton(
                                TypeRoles.USER.toString()
                        )
        );
        return applicationMappers.convertEntityToDto(
                usersRepository.save(user)
        );
    }



    @Override
    public List<UserDto> getAllUsers() {
        List<User> listUser =  usersRepository.findAll();
        return listUser.stream().map(users ->applicationMappers.convertEntityToDto(users)).collect(Collectors.toList());

    }

    @Override
    public UserDto getOneUser(int id) {
        User user =  usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not find"));
        return applicationMappers.convertEntityToDto(user);

    }

    @Override
    public void deleteUser(int id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not find to delete"));
        usersRepository.delete(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        if(getOneUser(id) ==null) new RuntimeException("l'user que vous vouliez modifier n'existe pas");
        User user = applicationMappers.convertDtoToEntity(userDto);
        user.setId(id);
        if(user.getRoles() == null){
            Collection<String> roles = usersRepository.findById(user.getId()).get().getRoles();
            user.setRoles(roles);
            }
       return applicationMappers.convertEntityToDto(usersRepository.save(user));
    }


}
