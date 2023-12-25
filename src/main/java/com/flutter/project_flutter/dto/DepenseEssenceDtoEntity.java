package com.flutter.project_flutter.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepenseEssenceDtoEntity {
    //private int id;

    private LocalDateTime date_preleve;
    private  float nbreLitreConsommer;
    private int station_id;
    private int abonnement_id;
}
