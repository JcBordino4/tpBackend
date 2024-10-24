package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Posiciones")
public class PosicionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private VehiculoEntity vehiculo;

    @Column(name = "FECHA_HORA")
    private Date fechaHora;

    private Double latitud;

    private Double longitud;

    public PosicionEntity(Integer id, VehiculoEntity vehiculo, Date fechaHora, Double latitud, Double longitud) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.fechaHora = fechaHora;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosicionEntity that = (PosicionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
