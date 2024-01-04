package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le nom de la station ne peut pas être vide.")
    private String stationName;

    private String longitude;
    private String latitude;
    private boolean status = true;
    @ManyToOne
    @JoinColumn(name = "partener_id")
    private User partener;
    @OneToMany(mappedBy = "station")
    private List<DepenseEssence> depenseEessences;

}
