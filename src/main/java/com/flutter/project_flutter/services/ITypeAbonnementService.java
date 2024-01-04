package com.flutter.project_flutter.services;

import com.flutter.project_flutter.dto.TypeAbonnementDto;

import java.util.List;

public interface ITypeAbonnementService {
    public TypeAbonnementDto registerTypeAbonnement(TypeAbonnementDto typeAbonnementDto);
    public List<TypeAbonnementDto> getAllTypeAbonnement();
    public TypeAbonnementDto getOneTypeAbonnement(int id);
    public void deleteTypeAbonnement(int id);
    TypeAbonnementDto updateTA(TypeAbonnementDto typeAbonnementDto, int id);

}
