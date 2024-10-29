package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "Modelos")
public class ModeloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MARCA")
    private MarcaEntity marca;

    private String descripcion;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private Set<VehiculoEntity> vehiculos;

}
