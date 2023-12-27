package com.flutter.project_flutter.controllers;

import com.flutter.project_flutter.constants.TypeRoles;
import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.servicesimpl.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOneUsers(@PathVariable int id){
        return ResponseEntity.ok(userService.getOneUser(id));
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.register(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> ModifyUser(@PathVariable int id,@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto, id));
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@Valid @RequestHeader("creator") UserDto creator,@Valid @RequestBody UserDto user) {
        if(creator.getRoles().contains(TypeRoles.ADMIN.toString())){
            return ResponseEntity.ok(userService.registerByAdmin(user));
        }
        return null;
    }

    @PostMapping("/{id}/{somme}")
    public  ResponseEntity <UserDto> RechargeSolde(@PathVariable("id") int id,@PathVariable("somme") BigDecimal somme){
        return ResponseEntity.ok(userService.rechargeSolde(id, somme));
    }
}
