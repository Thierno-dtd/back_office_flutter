package com.flutter.project_flutter.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbonnementDto {
    //private int id;
    private int typeAbonnement_id;
    private int client_id;



}
