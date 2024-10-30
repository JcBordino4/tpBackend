package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "Marcas")
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "marca", cascade = CascadeType.PERSIST)
    private Set<ModeloEntity> modelos = new HashSet<>();

}
