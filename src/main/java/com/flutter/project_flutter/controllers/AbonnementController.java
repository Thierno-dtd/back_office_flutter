package com.flutter.project_flutter.controllers;

import com.flutter.project_flutter.constants.AbonnementStaut;
import com.flutter.project_flutter.dto.AbonnementDto;
import com.flutter.project_flutter.dto.AbonnementDtoEntity;
import com.flutter.project_flutter.servicesimpl.AbonnementServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/abonnements")
public class AbonnementController {
    private AbonnementServices abonnementServices;

    @GetMapping("/")
    public ResponseEntity<List<AbonnementDtoEntity>> getAllAbonnement(){
        return ResponseEntity.ok(abonnementServices.getAllAbonnement());
    }

    @GetMapping("/AbonnementId/{id}")
    public ResponseEntity<AbonnementDtoEntity> getOneAbonnement(@PathVariable int id){
        return ResponseEntity.ok(abonnementServices.getOneAbonnement(id));
    }

    @GetMapping("/ClientId/{id}")
    public ResponseEntity<List<AbonnementDtoEntity>> getAllAbonnementByClientId(@PathVariable int id){
        return ResponseEntity.ok(abonnementServices.getAllAbonnementByClientId(id));
    }

    @GetMapping("/ClientId/{id}/{statut}")
    public ResponseEntity<List<AbonnementDtoEntity>> getAllAbonnementByClientIdAndStatut(@PathVariable int id, @PathVariable AbonnementStaut statut){
        return ResponseEntity.ok(abonnementServices.getAllAbonnementByClientIdAndStatut(id, statut));
    }

    @PostMapping("/")
    public ResponseEntity<AbonnementDtoEntity> register(@RequestBody AbonnementDto abonnementDto){
        return  ResponseEntity.ok(abonnementServices.registerAbonnement(abonnementDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonnement(@PathVariable int id){
        abonnementServices.deleteAbonnement(id);
        return ResponseEntity.accepted().build();
    }
}
