package com.flutter.project_flutter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flutter.project_flutter.entites.TypeAbonnement;
import com.flutter.project_flutter.entites.User;
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
public class AbonnementDto {
    //private int id;
    private int typeAbonnement_id;
    private int client_id;



}
