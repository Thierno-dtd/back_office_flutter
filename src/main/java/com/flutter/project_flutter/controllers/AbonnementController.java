package com.flutter.project_flutter.controllers;

import com.flutter.project_flutter.dto.AbonnementDto;
import com.flutter.project_flutter.dto.AbonnementDtoEntity;
import com.flutter.project_flutter.servicesimpl.AbonnementServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AbonnementController {
    private AbonnementServices abonnementServices;

    @GetMapping("/abonnements")
    public ResponseEntity<List<AbonnementDtoEntity>> getAllAbonnement(){
        return ResponseEntity.ok(abonnementServices.getAllAbonnement());
    }

    @GetMapping("/abonnements/{id}")
    public ResponseEntity<AbonnementDtoEntity> getOneAbonnement(@PathVariable int id){
        return ResponseEntity.ok(abonnementServices.getOneAbonnement(id));
    }

    @PostMapping("/abonnements")
    public ResponseEntity<AbonnementDtoEntity> register(@RequestBody AbonnementDto abonnementDto){
        return  ResponseEntity.ok(abonnementServices.registerAbonnement(abonnementDto));
    }

    @DeleteMapping("/abonnements/{id}")
    public ResponseEntity<Void> deleteAbonnement(@PathVariable int id){
        abonnementServices.deleteAbonnement(id);
        return ResponseEntity.accepted().build();
    }
}
