package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.Banner;

import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Vehiculos")
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    private ModeloEntity modelo;

    private Integer anio;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private Set<PruebaEntity> pruebas;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
    private Set<PosicionEntity> posiciones;

    public VehiculoEntity(Integer id, String patente, ModeloEntity modelo, Integer anio) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.anio = anio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiculoEntity that = (VehiculoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
