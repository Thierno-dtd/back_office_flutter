package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.constants.AbonnementStaut;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
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
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtMinusTwoDays = ldt.minusDays(2);
        //abonnement.setDateFin(ldt.minusDays(1));
        //abonnement.setDateDebut(ldt.minusDays(1));
        abonnement.setDateDebut(ldt);
        //abonnement.setDateFin(ldt.plusDays(1));
        abonnement.setDateFin(ldt.plusMonths(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getDuree()));
        abonnement.setNbre_litre(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getNbre_litre());
        abonnement.setNbre_litre_use(0);
        abonnement.setStatut(AbonnementStaut.ACTIVE);
        UserDto client = userService.getOneUser(abonnement.getClient().getId());
        float nbreLitreRecuperer = RecupererLesLitresRestantsDeSonAbonnementsDontLeDelaiAExpiree(
                abonnement
                , ldtMinusTwoDays
                , ldt
        );
        //log.info("le nombre de litre est "+nbreLitreRecuperer);
        abonnement.setBonus(nbreLitreRecuperer);
        if(abonnement.getPrix().compareTo(client.getSolde()) > 0){
            throw new InvalidEntityException("Votre solde est insuffisant pour souscrire cette abonnement");
        }
        client.setSolde((client.getSolde().subtract(abonnement.getPrix())));
        userService.updateUser(client, abonnement.getClient().getId());
        return applicationMappers.convertEntityToDto(
                abonnementRepository.save(abonnement)
        );
    }

    public AbonnementDtoEntity registerAbonnement(AbonnementDto abonnementDto, int delai) {

        Abonnement abonnement = applicationMappers.convertDtoToEntity(abonnementDto);
        abonnement.setPrix(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getPrix());
        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.plusDays(delai);
        LocalDateTime ldtMinusTwoDays = ldt.minusDays(2);
        //abonnement.setDateFin(ldt.minusDays(1));
        //abonnement.setDateDebut(ldt.minusDays(1));
        abonnement.setDateDebut(ldt);
        //abonnement.setDateFin(ldt.plusDays(1));
        abonnement.setDateFin(ldt.plusMonths(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getDuree()));
        abonnement.setNbre_litre(typeAbonnementService.getOneTypeAbonnement(abonnementDto.getTypeAbonnement_id()).getNbre_litre());
        abonnement.setNbre_litre_use(0);
        abonnement.setStatut(AbonnementStaut.ACTIVE);
        UserDto client = userService.getOneUser(abonnement.getClient().getId());
        float nbreLitreRecuperer = RecupererLesLitresRestantsDeSonAbonnementsDontLeDelaiAExpiree(
                abonnement
                , ldtMinusTwoDays
                , ldt
        );
        //log.info("le nombre de litre est "+nbreLitreRecuperer);
        abonnement.setBonus(nbreLitreRecuperer);
        if(abonnement.getPrix().compareTo(client.getSolde()) > 0){
            throw new InvalidEntityException("Votre solde est insuffisant pour souscrire cette abonnement");
        }
        client.setSolde((client.getSolde().subtract(abonnement.getPrix())));
        userService.updateUser(client, abonnement.getClient().getId());
        return applicationMappers.convertEntityToDto(
                abonnementRepository.save(abonnement)
        );
    }

    public void registerAbonnement(Abonnement abonnement) {
         abonnementRepository.save(abonnement);
    }

    @Transactional
    private float RecupererLesLitresRestantsDeSonAbonnementsDontLeDelaiAExpiree(Abonnement abonnement,LocalDateTime debutdelai, LocalDateTime finTime) {

        List<Abonnement> listDabonnementAvantFinDuDelai = abonnementRepository
                .findByClientIdAndDateDebutAfterAndDateFinBeforeAndStatut
                        (abonnement
                                        .getClient()
                                        .getId()
                                , debutdelai
                                , finTime
                                , AbonnementStaut.EXPIRE
                        );
        if (listDabonnementAvantFinDuDelai.isEmpty())
            return 0;
        else {
            int idPartener = typeAbonnementService.getOneTypeAbonnement(
                    abonnement
                            .getTypeAbonnement()
                            .getId()
                    )
                    .getPartener_id();
            List<Abonnement> result = listDabonnementAvantFinDuDelai
                    .stream()
                    .filter(obj -> obj.getTypeAbonnement().getPartenerTA().getId() == idPartener)
                    .collect(Collectors.toList());
            //result.forEach(abonn -> System.out.println(" :"+abonn.getNbre_litre()+"__:"+abonn.getBonus()+"nus : "+abonn.getNbre_litre_use()));
            if (result.isEmpty()) {
                return 0;
            } else {
                float litrerecuperer = 0;
                for(Abonnement ab: result){
                    litrerecuperer+= ab.getNbre_litre()+ab.getBonus() - ab.getNbre_litre_use();
                    ab.setNbre_litre_use(ab.getNbre_litre()+ab.getBonus());
                    abonnementRepository.save(ab);
                }
                System.out.println("Le result est : "+ litrerecuperer);
                return litrerecuperer;
            }
        }
    }

    private int compareDate(LocalDateTime date1, LocalDateTime date2){
        return date1.compareTo(date2);
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
    public List<AbonnementDtoEntity> getAllAbonnementByClientId(int id) {
        List<Abonnement> listAbonnement = abonnementRepository.findByClientId(id);
        if(listAbonnement == null){
           throw  new EntityNotFoundException("abonnement not found");
        }
        return listAbonnement.stream().map(la ->applicationMappers.convertEntityToDto(la)).collect(Collectors.toList());
    }

    @Override
    public List<AbonnementDtoEntity> getAllAbonnementByClientIdAndStatut(int id, AbonnementStaut statut) {
        List<Abonnement> listAbonnement = abonnementRepository.findByClientIdAndStatut(id, statut);
        if(listAbonnement == null){
            throw  new EntityNotFoundException("abonnement not found");
        }
        return listAbonnement.stream().map(la ->applicationMappers.convertEntityToDto(la)).collect(Collectors.toList());
    }

    @Transactional
    public List<Abonnement> getAllAbonnementWhichWillBeExpire(LocalDateTime deadLineForExpiration) {
        List<Abonnement> listAbonnement = abonnementRepository.findByStatutAndDateFinBefore(AbonnementStaut.ACTIVE, deadLineForExpiration);
        return listAbonnement;
    }

    @Override
    public void deleteAbonnement(int id) {
        Abonnement abonnement  = abonnementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abonnement not found to delete"));
        abonnementRepository.delete(abonnement);
    }

}
