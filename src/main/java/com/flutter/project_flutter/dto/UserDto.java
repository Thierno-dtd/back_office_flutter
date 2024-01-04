package com.flutter.project_flutter.dto;

import com.flutter.project_flutter.constants.TypeRoles;
import com.flutter.project_flutter.entites.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    //private int id;
    private String name;
    private String pname;
    private String email;
    private String passwd;
    private BigDecimal solde;

    private TypeRoles roles;

    public static UserDto fromEntity(User user){
        if(user==null){
            return null;
        }
        return UserDto.builder()
                //.id(user.getId())
                .name(user.getName())
                .passwd(user.getPasswd())
                .pname(user.getPname())
                .email(user.getEmail())
                .roles(user.getRoles())
                .solde(user.getSolde())
                .build();
    }
    public static User toEntity(UserDto userDto){
        if(userDto==null){
            return null;
        }
        User user= new User();
       // user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPname(userDto.getPname());
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles());
        user.setPasswd(userDto.getPasswd());
        user.setSolde(userDto.getSolde());
        return  user;
    }
}
