package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.dto.AbonnementDto;
import com.flutter.project_flutter.dto.AbonnementDtoEntity;
import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.entites.Abonnement;
import com.flutter.project_flutter.exceptions.EntityNotFoundException;
import com.flutter.project_flutter.exceptions.InvalidEntityException;
import com.flutter.project_flutter.mappers.ApplicationMappers;
import com.flutter.project_flutter.repositories.AbonnementRepository;
import com.flutter.project_flutter.services.IAbonnementServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AbonnementServices implements IAbonnementServices {
    private AbonnementRepository abonnementRepository;
    private ApplicationMappers applicationMappers;
    private TypeAbonnementService typeAbonnementService;
    private UserService userService;
    @Override
    public AbonnementDtoEntity registerAbonnement(AbonnementDto abonnementDto) {

        Abonnement abonnement = applicationMappers.convertDtoToEntity(abonnementDto);
        abonnement.setPrix(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getPrix());
        abonnement.setNbre_litre(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getNbre_litre());
        LocalDateTime ldt = LocalDateTime.now();
        abonnement.setDateDebut(ldt.minusDays(1));
        abonnement.setDateFin(ldt.minusDays(1));
        // de base c'est ldt dans le setDateDebut dans le setDateFin c'est s'instruction que j'ai commenté en bas
        //ldt.plusMonths(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getDuree())
        abonnement.setNbre_litre_use(0);
        UserDto client = userService.getOneUser(abonnement.getClient().getId());

        if(abonnement.getPrix().compareTo(client.getSolde()) > 0){
            throw new InvalidEntityException("Votre solde est insuffisant pour souscrire cette abonnement");
        }
        client.setSolde((client.getSolde().subtract(abonnement.getPrix())));
        userService.updateUser(client, abonnement.getClient().getId());
        return applicationMappers.convertEntityToDto(
                abonnementRepository.save(abonnement)
        );
    }

    @Override
    public List<AbonnementDtoEntity> getAllAbonnement() {
        List<Abonnement> listAbonnement =  abonnementRepository.findAll();
        return listAbonnement.stream().map(la ->applicationMappers.convertEntityToDto(la)).collect(Collectors.toList());

    }

    @Override
    public AbonnementDtoEntity getOneAbonnement(int id) {
        Abonnement abonnement =  abonnementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("abonnement not found"));
        return applicationMappers.convertEntityToDto(abonnement);
    }

    @Override
    public void deleteAbonnement(int id) {
        Abonnement abonnement  = abonnementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abonnement not found to delete"));
        abonnementRepository.delete(abonnement);
    }
}
