package com.flutter.project_flutter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeAbonnementDto {
    //private int id;
    private String libelle;
    private  float nbre_litre;
    private BigDecimal prix;
    private int duree;
    private String description;
    private int partener_id;

}
