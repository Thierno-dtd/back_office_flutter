package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.constants.TypeRoles;
import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.entites.User;
import com.flutter.project_flutter.exceptions.EntityNotFoundException;
import com.flutter.project_flutter.exceptions.InvalidEntityException;
import com.flutter.project_flutter.mappers.ApplicationMappers;
import com.flutter.project_flutter.repositories.UserRepository;
import com.flutter.project_flutter.services.IUserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserServices {
    private final UserRepository usersRepository;
    private final ApplicationMappers applicationMappers;


    @Override
    public UserDto rechargeSolde(int id, BigDecimal somme) {
        if(somme.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidEntityException("Le montant est est inférieur ou égale à 0");
        UserDto userDto = getOneUser(id);
        userDto.setSolde(somme.add(userDto.getSolde()));
        updateUser(userDto, id);
        return  userDto;
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> listUser =  usersRepository.findAll();
        return listUser.stream().map(users ->applicationMappers.convertEntityToDto(users)).collect(Collectors.toList());

    }

    @Override
    public UserDto getOneUser(int id) {
        User user =  usersRepository.findById(id).orElse(null);
        if(user == null) throw new EntityNotFoundException("user not find");
        return applicationMappers.convertEntityToDto(user);

    }

    @Override
    public void deleteUser(int id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not find to delete"));
        usersRepository.delete(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        if(getOneUser(id) ==null) new InvalidEntityException("l'user que vous vouliez modifier n'existe pas");
        User user = applicationMappers.convertDtoToEntity(userDto);
        user.setId(id);
        if(user.getRoles() == null){
           TypeRoles roles = usersRepository.findById(user.getId()).get().getRoles();
            user.setRoles(roles);
            }
       return applicationMappers.convertEntityToDto(usersRepository.save(user));
    }


}
