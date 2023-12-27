package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    private LocalDateTime date_preleve;
    @NotNull
    @NotEmpty
    private  float nbreLitreConsommer;
    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
    @ManyToOne
    @NotNull
    @NotEmpty
    @JoinColumn(name = "abonnement_id")
    private Abonnement abonnements;
}
