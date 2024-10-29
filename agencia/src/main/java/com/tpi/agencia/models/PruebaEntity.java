package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Data
@Table(name = "Pruebas")
public class PruebaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private VehiculoEntity vehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    private EmpleadoEntity empleado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    private InteresadoEntity interesado;

    @Column(name = "FECHA_HORA_INICIO")
    private Date fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private Date fechaHoraFin;

    private String comentarios;

    public PruebaEntity(Integer id, VehiculoEntity vehiculo, EmpleadoEntity empleado, InteresadoEntity interesado, Date fechaHoraInicio, Date fechaHoraFin, String comentarios) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.empleado = empleado;
        this.interesado = interesado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PruebaEntity that = (PruebaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
