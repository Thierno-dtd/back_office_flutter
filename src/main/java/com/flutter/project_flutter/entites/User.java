package com.flutter.project_flutter.entites;

import com.flutter.project_flutter.constants.TypeRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le champ nom ne peut pas être vide.")
    private String name;
    @NotBlank(message = "Le champ prénom ne peut pas être vide.")
    private String pname;
    @Email(message = "L'adresse email n'est pas valide.")
    private String email;
    @NotBlank(message = "Le champs password ne peut pas être vide.")
    private String passwd;
    @NotBlank(message = "Le champs solde ne peut pas être vide.")
    private BigDecimal solde;
    @Column(name = "userRole",nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeRoles roles;
    @OneToMany(mappedBy = "partener")
    private List<Station> stations;
    @OneToMany(mappedBy = "partenerTA")
    private List<TypeAbonnement> typeAbonnements;
    @OneToMany(mappedBy = "client")
    private List<Abonnement> abonnements;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
