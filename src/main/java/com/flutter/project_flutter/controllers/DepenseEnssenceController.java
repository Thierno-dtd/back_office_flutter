package com.flutter.project_flutter.controllers;

import com.flutter.project_flutter.dto.DepenseEssenceDto;
import com.flutter.project_flutter.dto.DepenseEssenceDtoEntity;
import com.flutter.project_flutter.servicesimpl.DepenseEssenceServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor
@RequestMapping("/depenseEssence")
public class DepenseEnssenceController {
    private DepenseEssenceServices depenseEssenceServices;

    @GetMapping("/")
    public ResponseEntity<List<DepenseEssenceDtoEntity>> getAllDepenseEssence(){return ResponseEntity.ok(depenseEssenceServices.getAllDepenseEssence());}

    @GetMapping("/{id}")
    public ResponseEntity<DepenseEssenceDtoEntity> getOneDepenseEssence(@PathVariable int id){return ResponseEntity.ok(depenseEssenceServices.getOneDepenseEssence(id));}

    @PostMapping("/")
    public ResponseEntity<DepenseEssenceDtoEntity> registerDepenseEssence(@RequestBody DepenseEssenceDto depenseEssenceDto){return ResponseEntity.ok(depenseEssenceServices.registerDepenseEssence(depenseEssenceDto));}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeenseEssence(@PathVariable int id){
        depenseEssenceServices.deleteDepenseEssence(id);
        return ResponseEntity.accepted().build();
    }
}
