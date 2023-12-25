package com.flutter.project_flutter.controllers;

import com.flutter.project_flutter.dto.StationDto;
import com.flutter.project_flutter.servicesimpl.StationServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stations")
public class StationController {
    private StationServices stationServices;
    @GetMapping("/")
    public ResponseEntity<List<StationDto>> getAllStation(){
        return ResponseEntity.ok(stationServices.getAllStation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDto> getAllStation(@PathVariable int id){
        return ResponseEntity.ok(stationServices.getOneStation(id));
    }

    @PostMapping("/")
    public ResponseEntity<StationDto> register(@RequestBody StationDto stationDto){
        return ResponseEntity.ok(stationServices.registerStation(stationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable int id){
        stationServices.deleteStation(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationDto> updateStation (@RequestBody StationDto stationDto, @PathVariable int id){
        return ResponseEntity.ok(stationServices.updateStation(stationDto, id));
    }
}
