package com.flutter.project_flutter.validor;

import com.flutter.project_flutter.dto.TypeAbonnementDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TypeAbonnementValidator {

    public static List<String> validate (TypeAbonnementDto typeAbonnementDto){
        List<String> errors = new ArrayList<>();
        if(typeAbonnementDto == null){
            errors.add("vous devriez ajouter un libellé pour le type d'abonnement.");
            errors.add("vous devriez ajouter le nombre de litre assigné à type.");
            errors.add("vous devriez ajouter le prix de ce type.");
            errors.add("vous devriez ajouter la durée de l'abonnement en mois.");
            return errors;
        }
        if(!StringUtils.hasLength(typeAbonnementDto.getLibelle())) errors.add("Vueillez renseignez le libellé, c'est obligatoire");
        if(typeAbonnementDto.getPrix().compareTo(BigDecimal.ZERO)<=0) errors.add(" le prix doit etre positif");
        if(typeAbonnementDto.getNbre_litre() <= 0) errors.add("le nombre de litre doit etre positif ");
        if(typeAbonnementDto.getDuree() <= 0) errors.add(" la durée doit etre positive");
        return errors;
    }
}
