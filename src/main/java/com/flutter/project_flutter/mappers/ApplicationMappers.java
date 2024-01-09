package com.flutter.project_flutter.mappers;

import com.flutter.project_flutter.dto.*;
import com.flutter.project_flutter.entites.*;
import com.flutter.project_flutter.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMappers {
    public UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public User convertDtoToEntity(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public TypeAbonnementDto convertEntityToDto(TypeAbonnement typeAbonnement){
        TypeAbonnementDto typeAbonnementDto = new TypeAbonnementDto();
        BeanUtils.copyProperties(typeAbonnement, typeAbonnementDto);
        typeAbonnementDto.setPartener_id(typeAbonnement.getPartenerTA().getId());
        return typeAbonnementDto;
    }

    public TypeAbonnement convertDtoToEntity(TypeAbonnementDto typeAbonnementDto){
        TypeAbonnement typeAbonnement = new TypeAbonnement();
        BeanUtils.copyProperties(typeAbonnementDto, typeAbonnement);
        typeAbonnement.setPartenerTA(User.builder().id(typeAbonnementDto.getPartener_id()).build());
        return typeAbonnement;

    }

    public StationDto convertEntityToDto(Station station){
        StationDto stationDto = new StationDto();
        BeanUtils.copyProperties(station, stationDto);
        stationDto.setPartener_id(station.getPartener().getId());
        return stationDto;
    }

    public Station convertDtoToEntity(StationDto stationDto){
        Station station = new Station();
        BeanUtils.copyProperties(stationDto, station);
        station.setPartener(User.builder().id(stationDto.getPartener_id()).build());
        return station;
    }

    public DepenseEssenceDtoEntity convertEntityToDto(DepenseEssence depenseEssence){
        DepenseEssenceDtoEntity depenseEssenceDtoEntity = new DepenseEssenceDtoEntity();
        BeanUtils.copyProperties(depenseEssence, depenseEssenceDtoEntity);
        depenseEssenceDtoEntity.setStation_id(depenseEssence.getStation().getId());
        depenseEssenceDtoEntity.setAbonnement_id(depenseEssence.getAbonnements().getId());
        return depenseEssenceDtoEntity;
    }

    public DepenseEssence  convertDtoToEntity(DepenseEssenceDto depenseEssenceDto){
        DepenseEssence depenseEssence = new DepenseEssence();
        BeanUtils.copyProperties(depenseEssenceDto, depenseEssence);
        depenseEssence
                .setStation(
                        Station
                                .builder()
                                .id(
                                        depenseEssenceDto
                                                .getStation_id()
                                )
                                .build()
                );
        depenseEssence
                .setAbonnements(
                        Abonnement
                                .builder()
                                .id(
                                        depenseEssenceDto
                                        .getAbonnement_id()
                                )
                                .build()
                );
        return depenseEssence;
    }

    public AbonnementDtoEntity convertEntityToDto(Abonnement abonnement){
        AbonnementDtoEntity abonnementDtoEntity = new AbonnementDtoEntity();
        BeanUtils.copyProperties(abonnement, abonnementDtoEntity);
        abonnementDtoEntity.setDate_debut(abonnement.getDateDebut());
        abonnementDtoEntity.setDate_fin(abonnement.getDateFin());
        abonnementDtoEntity.setTypeAbonnement_id(abonnement.getTypeAbonnement().getId());
        abonnementDtoEntity.setClient_id(abonnement.getClient().getId());
        abonnementDtoEntity.setStaut(abonnement.getStatut());
        return abonnementDtoEntity;
    }

    public Abonnement  convertDtoToEntity(AbonnementDto abonnementDto){
        Abonnement abonnement = new Abonnement();
        BeanUtils.copyProperties(abonnementDto, abonnement);
        abonnement.setTypeAbonnement(TypeAbonnement.builder()
                .id(abonnementDto.getTypeAbonnement_id()).build());
        abonnement.setClient(User.builder().id(abonnementDto.getClient_id()).build());
        return abonnement;
    }
}
