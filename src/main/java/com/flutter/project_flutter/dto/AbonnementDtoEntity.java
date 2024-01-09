package com.flutter.project_flutter.dto;

import com.flutter.project_flutter.constants.AbonnementStaut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbonnementDtoEntity {

    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    private float nbre_litre;
    private float bonus;
    private BigDecimal prix;
    private float nbre_litre_use;
    private AbonnementStaut staut;
    private int typeAbonnement_id;
    private int client_id;

}
