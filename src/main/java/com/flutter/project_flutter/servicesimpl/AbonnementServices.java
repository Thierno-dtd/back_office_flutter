package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.dto.AbonnementDto;
import com.flutter.project_flutter.dto.AbonnementDtoEntity;
import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.entites.Abonnement;
import com.flutter.project_flutter.entites.User;
import com.flutter.project_flutter.mappers.ApplicationMappers;
import com.flutter.project_flutter.repositories.AbonnementRepository;
import com.flutter.project_flutter.repositories.UserRepository;
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

        //Optional<Abonnement> findstation = this.abonnementRepository.findById(abonnementDto.getId());
        //if(findstation.isPresent() || abonnementDto == null) throw new RuntimeException("Ce abonnement est deja utiliser");
        Abonnement abonnement = applicationMappers.convertDtoToEntity(abonnementDto);
        abonnement.setPrix(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getPrix());
        abonnement.setNbre_litre(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getNbre_litre());
        LocalDateTime ldt = LocalDateTime.now();
        abonnement.setDate_debut(ldt);
        abonnement.setNbre_litre_use(0);
        UserDto client = userService.getOneUser(abonnement.getClient().getId());

        if(abonnement.getPrix().compareTo(client.getSolde()) > 0){
            new RuntimeException("Votre solde est insuffisant pour souscrire cette abonnement");
            return AbonnementDtoEntity.builder().build();
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
        Abonnement abonnement =  abonnementRepository.findById(id).orElseThrow(() -> new RuntimeException("abonnement not find"));
        return applicationMappers.convertEntityToDto(abonnement);
    }

    @Override
    public void deleteAbonnement(int id) {
        Abonnement abonnement  = abonnementRepository.findById(id).orElseThrow(() -> new RuntimeException("Abonnement not find to delete"));
        abonnementRepository.delete(abonnement);
    }
}
