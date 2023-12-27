package com.flutter.project_flutter.validor;

import com.flutter.project_flutter.dto.UserDto;
import org.apache.commons.lang3.StringUtils;

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
        if(userDto.getPname() == null) errors.add("Vous devez ajouter un prenom !");
        if(userDto.getEmail() == null) errors.add("Vous devez ajouter un mail!");
        if(userDto.getPasswd() == null) errors.add("Vous devez ajouter un Mot de passe!");

        return errors;
    }
}
