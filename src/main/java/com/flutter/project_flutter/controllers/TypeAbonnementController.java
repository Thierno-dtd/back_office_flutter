package com.flutter.project_flutter.controllers;

import com.flutter.project_flutter.dto.TypeAbonnementDto;
import com.flutter.project_flutter.servicesimpl.TypeAbonnementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/typeAbonnements")
public class TypeAbonnementController {
    private TypeAbonnementService typeAbonnementService;

    @GetMapping("/")
    public ResponseEntity<List<TypeAbonnementDto>> getAllTypeAbonnement(){
        return ResponseEntity.ok(typeAbonnementService.getAllTypeAbonnement());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeAbonnementDto> getOneTypeAbonnement(@PathVariable int id){
        return ResponseEntity.ok(typeAbonnementService.getOneTypeAbonnement(id));
    }

    @PostMapping("/")
    public ResponseEntity<TypeAbonnementDto> registrer(@RequestBody TypeAbonnementDto typeAbonnementDto){
        return ResponseEntity.ok(typeAbonnementService.registerTypeAbonnement(typeAbonnementDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeAbonnement(@PathVariable int id){
        typeAbonnementService.deleteTypeAbonnement(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeAbonnementDto> updateTA(@RequestBody TypeAbonnementDto typeAbonnementDto, @PathVariable int id){
        return ResponseEntity.ok(typeAbonnementService.updateTA(typeAbonnementDto, id));
    }
}
