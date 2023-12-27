package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.dto.DepenseEssenceDto;
import com.flutter.project_flutter.dto.DepenseEssenceDtoEntity;
import com.flutter.project_flutter.entites.Abonnement;
import com.flutter.project_flutter.entites.DepenseEssence;
import com.flutter.project_flutter.exceptions.EntityNotFoundException;
import com.flutter.project_flutter.exceptions.InvalidOperationException;
import com.flutter.project_flutter.mappers.ApplicationMappers;
import com.flutter.project_flutter.repositories.AbonnementRepository;
import com.flutter.project_flutter.repositories.DepenseEssenceRepository;
import com.flutter.project_flutter.services.IDepenseEssenceServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class DepenseEssenceServices implements IDepenseEssenceServices {
    private DepenseEssenceRepository depenseEssenceRepository;
    private ApplicationMappers applicationMappers;
    private AbonnementRepository abonnementRepository;
    @Override
    public DepenseEssenceDtoEntity registerDepenseEssence(DepenseEssenceDto depenseEssenceDto) {
        //Optional<DepenseEssence> findDepenseEssence = this.depenseEssenceRepository.findById(depenseEssenceDto.getId());
        //if(findDepenseEssence.isPresent() || depenseEssenceDto == null) throw new RuntimeException("Ce depenseEssence est deja utiliser");
        DepenseEssence depenseEssence = applicationMappers.convertDtoToEntity(depenseEssenceDto);
        System.out.println(depenseEssence);
        Abonnement abonnement = abonnementRepository
                .findById(
                        depenseEssence
                                .getAbonnements()
                                .getId()
                )
                .get();
float nbre_litre_restant = abonnement.getNbre_litre()-abonnement.getNbre_litre_use();
        if ( nbre_litre_restant < depenseEssence.getNbreLitreConsommer()) {
            if (nbre_litre_restant == 0.0f) {
                new InvalidOperationException("Vous aviez plus de litre d'essence disponible pour cette abonnement ");
                return DepenseEssenceDtoEntity.builder().build();
            }
            else {
                new InvalidOperationException("Le nombre de litre consommé est superieur que ce que vous aviez sur le compte, il vous reste a payer pour "+(depenseEssence.getNbreLitreConsommer()-nbre_litre_restant)+"l.");
                abonnement
                        .setNbre_litre_use(
                                abonnement
                                        .getNbre_litre()
                        );
                abonnementRepository.save(abonnement);

            }
        }
        else {
            abonnement
                    .setNbre_litre_use(abonnement.getNbre_litre_use()+depenseEssence.getNbreLitreConsommer());
            abonnementRepository.save(abonnement);
        }
        depenseEssence.setDate_preleve(LocalDateTime.now());
        return  applicationMappers.convertEntityToDto(
                depenseEssenceRepository.save(depenseEssence)
        );
    }
    @Override
    public List<DepenseEssenceDtoEntity> getAllDepenseEssence() {
        List<DepenseEssence> listDepenseEssence =  depenseEssenceRepository.findAll();
        return listDepenseEssence.stream().map(lde ->applicationMappers.convertEntityToDto(lde)).collect(Collectors.toList());

    }

    @Override
    public DepenseEssenceDtoEntity getOneDepenseEssence(int id) {
        DepenseEssence depenseEssence =  depenseEssenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("depenseEssence not find"));
        return applicationMappers.convertEntityToDto(depenseEssence);
    }

    @Override
    public void deleteDepenseEssence(int id) {
        DepenseEssence depenseEssence  = depenseEssenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("depenseEssence not find to delete"));
        depenseEssenceRepository.delete(depenseEssence);
    }
}
