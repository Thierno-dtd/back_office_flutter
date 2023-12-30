package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.dto.TypeAbonnementDto;
import com.flutter.project_flutter.dto.UserDto;
import com.flutter.project_flutter.entites.TypeAbonnement;
import com.flutter.project_flutter.entites.User;
import com.flutter.project_flutter.exceptions.EntityNotFoundException;
import com.flutter.project_flutter.exceptions.InvalidEntityException;
import com.flutter.project_flutter.mappers.ApplicationMappers;
import com.flutter.project_flutter.repositories.TypeAbonnementRepository;
import com.flutter.project_flutter.services.ITypeAbonnementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TypeAbonnementService implements ITypeAbonnementService {
    private TypeAbonnementRepository typesAbonnementRepository;
    private ApplicationMappers applicationMappers;
    @Override
    public TypeAbonnementDto registerTypeAbonnement(TypeAbonnementDto typeAbonnementDto) {
        Optional<TypeAbonnement> findTypeAbonne = this.typesAbonnementRepository.findByLibelle(typeAbonnementDto.getLibelle());
        if(findTypeAbonne.isPresent() || typeAbonnementDto == null) throw new InvalidEntityException("Ce type existe deja ou objet vide");
        TypeAbonnement typeAbonnement = applicationMappers.convertDtoToEntity(typeAbonnementDto);
        System.out.println(typeAbonnementDto);
        System.out.println(typeAbonnement);
        return applicationMappers
                .convertEntityToDto(
                typesAbonnementRepository
                        .save(typeAbonnement)
        );
    }

    @Override
    public List<TypeAbonnementDto> getAllTypeAbonnement() {
        List<TypeAbonnement> listTypeAbonnement =  typesAbonnementRepository.findAll();
        return listTypeAbonnement.stream().map(lta ->applicationMappers.convertEntityToDto(lta)).collect(Collectors.toList());

    }

    @Override
    public TypeAbonnementDto getOneTypeAbonnement(int id) {
        TypeAbonnement typeAbonnement =  typesAbonnementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeAbonnement not found"));
        return applicationMappers.convertEntityToDto(typeAbonnement);

    }

    @Override
    public void deleteTypeAbonnement(int id) {
        TypeAbonnement typeAbonnement = typesAbonnementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TypeAbonnement not find to delete"));
        typesAbonnementRepository.delete(typeAbonnement);
    }

    @Override
    public TypeAbonnementDto updateTA(TypeAbonnementDto typeAbonnementDto, int id) {
        if(getOneTypeAbonnement(id) == null) new EntityNotFoundException("Le type d'abonnelent que vous voulez modifier n'existe pas");
        TypeAbonnement typeAbonnement = applicationMappers.convertDtoToEntity(typeAbonnementDto);
        typeAbonnement.setId(id);
        return applicationMappers.convertEntityToDto(typesAbonnementRepository.save(typeAbonnement));
    }


}
