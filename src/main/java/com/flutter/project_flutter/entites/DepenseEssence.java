package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepenseEssence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date_preleve;
    private  float nbreLitreConsommer;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
    @ManyToOne
    @JoinColumn(name = "abonnement_id")
    private Abonnement abonnements;
}
