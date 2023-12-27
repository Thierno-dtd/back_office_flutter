package com.flutter.project_flutter.validor;

import com.flutter.project_flutter.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public List<String> validate(UserDto userDto){
        List<String> errors = new ArrayList<>();
        if(userDto == null){
            errors.add("Vous devez ajouter un nom!");
            errors.add("Vous devez ajouter un prenom !");
            errors.add("Vous devez ajouter un mail!");
            errors.add("Vous devez ajouter un Mot de passe!");
            return errors;
        }
        if(userDto.getName() == null){
            errors.add("Vous devez ajouter un nom!");
        }
        if(!StringUtils.hasLength(userDto.getPname())) errors.add("Vous devez ajouter un prenom !");
        if(!StringUtils.hasLength(userDto.getEmail())) errors.add("Vous devez ajouter un mail!");
        if(!StringUtils.hasLength(userDto.getPasswd())) errors.add("Vous devez ajouter un Mot de passe!");

        return errors;
    }
}
