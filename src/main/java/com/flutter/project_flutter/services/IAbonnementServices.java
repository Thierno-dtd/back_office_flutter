package com.flutter.project_flutter.services;

import com.flutter.project_flutter.dto.AbonnementDto;
import com.flutter.project_flutter.dto.AbonnementDtoEntity;

import java.util.List;

public interface IAbonnementServices {
    public AbonnementDtoEntity registerAbonnement(AbonnementDto stationDto);
    public List<AbonnementDtoEntity> getAllAbonnement();
    public AbonnementDtoEntity getOneAbonnement(int id);
    public void deleteAbonnement(int id);
}
