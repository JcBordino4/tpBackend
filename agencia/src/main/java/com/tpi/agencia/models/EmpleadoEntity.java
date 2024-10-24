package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Empleados")
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer legajo;

    private String nombre;

    private String apellido;

    @Column(name = "telefono_contacto")
    private Integer telefonoContacto;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Set<PruebaEntity> pruebas = new HashSet<>();

    public EmpleadoEntity(Integer legajo, String nombre, String apellido, Integer telefonoContacto) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonoContacto = telefonoContacto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoEntity that = (EmpleadoEntity) o;
        return Objects.equals(legajo, that.legajo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
    }
}
