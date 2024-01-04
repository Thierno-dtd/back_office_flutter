package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TypeAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le champ Libelle du type d'abonnement ne peut pas être vide.")
    private String libelle;
    @NotBlank(message = "Le nombre de litre du type d'abonnemen ne peut pas être vide.")
    private  float nbre_litre;
    @NotBlank(message = "Le prix du type d'abonnement ne peut pas être vide.")
    private BigDecimal prix;

    private String description;
    @NotBlank(message = "Le nombre de mois pour ce type d'abonnement ne peut pas etre vide")
    private int duree;

    @ManyToOne
    @JoinColumn(name = "partener_id")
    private User partenerTA;
    @OneToMany(mappedBy = "typeAbonnement")
    private List<Abonnement> abonnements;
}
